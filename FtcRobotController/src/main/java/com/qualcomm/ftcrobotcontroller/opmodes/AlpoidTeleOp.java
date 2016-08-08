package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class AlpoidTeleOp extends OpMode {

    DcMotor f1;
    DcMotor f2;
    DcMotor b3;
    DcMotor b4;
    DcMotor t2;
    Servo fs1;
    Servo ss1;
  //  Servo ss2;

    final static double ARM_MIN_RANGE = 0.00;
    final static double ARM_MAX_RANGE = 1.00;
    double ap;
    double ad;
    double ap1;

    private void b() {
        f1.setPower(-gamepad1.right_stick_y);
        f2.setPower(-gamepad1.right_stick_y);
        b3.setPower(-gamepad1.left_stick_y);
        b4.setPower(-gamepad1.left_stick_y);
        t2.setPower(-gamepad2.left_stick_y);
        b3.setDirection(DcMotor.Direction.REVERSE);
        b4.setDirection(DcMotor.Direction.REVERSE);
        t2.setDirection(DcMotor.Direction.REVERSE);
    }

    private void n() {
        f1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        f2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        b3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        b4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        t2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    private void i() {
        ad = 0.2;
        f1 = hardwareMap.dcMotor.get("f1");
        f2 = hardwareMap.dcMotor.get("f2");
        b3 = hardwareMap.dcMotor.get("b3");
        b4 = hardwareMap.dcMotor.get("b4");
        t2 = hardwareMap.dcMotor.get("t2");
        fs1 = hardwareMap.servo.get("fs1");
        ss1 = hardwareMap.servo.get("servo1");
    }

    private void t() {
        telemetry.addData("f1p", f1.getCurrentPosition());
        telemetry.addData("f2p", f2.getCurrentPosition());
        telemetry.addData("f3p", b3.getCurrentPosition());
        telemetry.addData("f4p", b4.getCurrentPosition());
        telemetry.addData("t2p", t2.getCurrentPosition());
        telemetry.addData("fs1", fs1.getPosition() + " / " + fs1.getPortNumber());
        telemetry.addData("ss1", ss1.getPosition() + " / " + ss1.getPortNumber());
    }

    private void s(float p) {
        f1.setPower(p);
        f2.setPower(p);
        b3.setPower(p);
        b4.setPower(p);
        t2.setPower(p);
    }

    private void j() {
        ap = Range.clip(ap, ARM_MIN_RANGE, ARM_MAX_RANGE);
        ap1 = Range.clip(ap1, ARM_MIN_RANGE, ARM_MAX_RANGE);
        fs1.setPosition(ap);
        ss1.setPosition(ap1);
        if (gamepad1.dpad_left) {
            ap -= ad;
        }
        if (gamepad1.dpad_right) {
            ap += ad;
        }

        if (gamepad1.right_bumper) {
            ap = 0.5;
        }

     /*   if(gamepad2.dpad_left){
            ap1+=ad1;
        }
        if(gamepad2.dpad_right){
            ap1-=ad1;
        } */

        if (gamepad2.a) {
            // if the A button is pushed on gamepad1, increment the position of
            // the arm servo.
            armPosition += armDelta;
        }

        if (gamepad2.y) {
            // if the Y button is pushed on gamepad1, decrease the position of
            // the arm servo.
            armPosition -= armDelta;
        }

        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        ss1.setPosition(armPosition );
    }

    @Override
    public void init() {
        i(); //init components
        t(); //ads telemetry
        n(); //resets encoders
    }

    // position of the arm servo.
    double armPosition;

    // amount to change the arm servo position.
    double armDelta = 0.1;




    @Override
    public void loop() {
        t(); //updates telemetry
        b(); //moves motors
        j(); //controls servos
        armPosition = 0.2;
    }

    @Override
    public void stop() {
        s(0); //sets motor power to 0
        n(); //Resets Encoders
    }
}
