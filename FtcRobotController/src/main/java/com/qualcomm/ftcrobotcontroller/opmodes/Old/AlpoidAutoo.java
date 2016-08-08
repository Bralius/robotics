package com.qualcomm.ftcrobotcontroller.opmodes.Old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

public class AlpoidAutoo extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    Servo leftArm;
    Servo rightArm;


    public void goForward(float MOTOR_SPEED){
        motorFrontLeft.setPower(MOTOR_SPEED);
        motorFrontRight.setPower(MOTOR_SPEED);
        motorBackLeft.setPower(MOTOR_SPEED);
        motorBackRight.setPower(MOTOR_SPEED);
    }

  /*  public void goReverse(float MOTOR_SPEED){}

    public void turnLeft(float MOTOR_SPEED){
        motorFrontRight.setPower(MOTOR_SPEED);
        motorBackRight.setPower(MOTOR_SPEED);
    }
*/

    private void initComponents() {
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        //motorLiftShovel = hardwareMap.dcMotor.get("test");
        //motorRaiseHook = hardwareMap.dcMotor.get("test");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);
        //motorLiftShovel.setDirection(DcMotor.Direction.REVERSE);
        //motorRaiseHook.setDirection(DcMotor.Direction.REVERSE);

        //Servos
        leftArm = hardwareMap.servo.get("servoLeftArm");
        rightArm = hardwareMap.servo.get("servoRightArm");
    }

    @Override
    public void init() {
        initComponents();
    }

    @Override
    public void loop() {
        motorBackRight.setPower(0.9);
       /* telemetry.addData("Motor Front Right", motorFrontRight.getCurrentPosition());
        telemetry.addData("Motor Back Right", motorBackRight.getCurrentPosition());
        telemetry.addData("Motor Front Left", motorFrontLeft.getCurrentPosition());
        telemetry.addData("Motor Back Left", motorBackRight.getCurrentPosition()); */
    }

    @Override
    public void stop(){
        motorBackRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontLeft.setMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontRight.setMode(DcMotorController.RunMode.RESET_ENCODERS);


    }

}
