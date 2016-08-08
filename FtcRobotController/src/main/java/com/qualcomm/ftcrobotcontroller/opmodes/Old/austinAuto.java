package com.qualcomm.ftcrobotcontroller.opmodes.Old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by Nolan on 12/18/2015.
 */
public class austinAuto extends OpMode{

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    @Override
    public void init() {
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

        resetEncoders();

    }

    float stop = 0;

    @Override
    public void loop() {
        runToPosition();
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
                    if(motorBackRight.getCurrentPosition() < 184333){
                        goFoward((float) 0.85);
                        goFoward(stop);
                    }
                }
            }

        }
        telemetry.addData("Motor Front Right", motorFrontRight.getCurrentPosition());
        telemetry.addData("Motor Back Right", motorBackRight.getCurrentPosition());
        telemetry.addData("Motor Front Left", motorFrontLeft.getCurrentPosition());
        telemetry.addData("Motor Back Left", motorBackLeft.getCurrentPosition());
    }

    @Override
    public void stop(){
        resetEncoders();
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
