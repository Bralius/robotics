package com.qualcomm.ftcrobotcontroller.opmodes.Alpoid;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Nolan on 12/18/2015.
 */
public class AlpoidAuto extends OpMode{



    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    Servo armLift;

    double armDelta = 0.1;
    double armPosition;
    final static double ARM_MIN_RANGE = 0.20;
    final static double ARM_MAX_RANGE = 0.90;

    @Override
    public void init() {
        armPosition = 0.2;
        armPosition += armDelta;
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        //motorLiftShovel = hardwareMap.dcMotor.get("test");
        //motorRaiseHook = hardwareMap.dcMotor.get("test");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        motorFrontRight.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.FORWARD);
        //motorLiftShovel.setDirection(DcMotor.Direction.REVERSE);
        //motorRaiseHook.setDirection(DcMotor.Direction.REVERSE);

        armLift = hardwareMap.servo.get("servoArmLift");

        resetEncoders();

    }

    float stop = 0;

    @Override
    public void loop() {
        runToPosition();

      //  armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        //armPosition += armDelta;
        //armLift.setPosition(armPosition);
        armLift.setPosition(0.9);

        if(motorBackRight.getCurrentPosition() < 4000){
            goFoward((float) 0.85);
        } else {
            goFoward(stop);
        }
        if(motorBackRight.getCurrentPosition() < 7600){
            turnLeft((float) 0.85);
        } else {
            goFoward(stop);
            if(motorBackRight.getCurrentPosition() < 8933){
                goFoward((float) 0.85);
            } else {
                goFoward(stop);
                if(motorBackRight.getCurrentPosition() < 10433){
                    turnLeft((float) 0.85);
                } else {
                    if(motorBackRight.getCurrentPosition() < 18433){
                        goFoward((float) 0.85);
                    }
                }
            }

        }
        telemetry.addData("Motor Front Right", motorFrontRight.getCurrentPosition());
        telemetry.addData("Motor Back Right", motorBackRight.getCurrentPosition());
        telemetry.addData("Motor Front Left", motorFrontLeft.getCurrentPosition());
        telemetry.addData("Motor Back Left", motorBackLeft.getCurrentPosition());
        telemetry.addData("Lefter kanker flikker bucket", armPosition);
    }

    @Override
    public void stop(){
        resetEncoders();
        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        armPosition-=armDelta;
        armLift.setPosition(0.2);
    }

    private void runToPosition(){
        motorBackRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

    private void resetEncoders(){
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    private void goFoward(float motorSpeed){
        motorFrontRight.setPower(motorSpeed);
        motorFrontLeft.setPower(motorSpeed);
        motorBackRight.setPower(motorSpeed);
        motorBackLeft.setPower(motorSpeed);
    }
    private  void turnRight(float motorSpeed){
        motorFrontLeft.setPower(motorSpeed);
        motorBackLeft.setPower(motorSpeed);
    }
    private  void turnLeft(float motorSpeed){
        motorFrontRight.setPower(motorSpeed);
        motorBackRight.setPower(motorSpeed);
    }
}
