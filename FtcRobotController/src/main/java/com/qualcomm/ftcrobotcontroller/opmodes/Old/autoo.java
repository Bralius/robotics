package com.qualcomm.ftcrobotcontroller.opmodes.Old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by robouser on 12/5/2015.
 */
public class autoo extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor motorLiftShovel;
    DcMotor motorRaiseHook;

    State progamPosition;

    private enum State {
        START,
        FORWARD,
        TURNATLINERIGHT,
        TURNATLINELEFT,
        END,
    }

    @Override
    public void init(){
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
        //motorLiftShovel = hardwareMap.dcMotor.get("test");
        //motorRaiseHook = hardwareMap.dcMotor.get("test");

        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorLiftShovel.setDirection(DcMotor.Direction.REVERSE);
        //motorRaiseHook.setDirection(DcMotor.Direction.REVERSE);
        progamPosition = State.START;
    }

    @Override
    public void loop(){
        runToPosition();

        switch (progamPosition){
            case START:
                if(motorBackRight.getCurrentPosition() == 0){
                    if(motorFrontRight.getCurrentPosition() == 0){
                        if(motorFrontLeft.getCurrentPosition() == 0){
                            if(motorBackLeft.getCurrentPosition() == 0){
                                progamPosition = State.FORWARD;
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                    break;
                } else {
                    break;
                }
            case FORWARD:
                goFoward((float) 0.85);
                progamPosition = State.TURNATLINELEFT;
                break;
            case TURNATLINELEFT:
                turnLeft((float) 1);
                progamPosition = State.TURNATLINERIGHT;
                break;
            case TURNATLINERIGHT:
                turnRight((float) 1);
                progamPosition = State.START;
                break;
            case END:
                goFoward(0);
                break;
        }

       /*

       if (treadLeft.getCurrentPosition() == 0){

                    if (treadRight.getCurrentPosition() == 0) {
                        programPosition = State.FORWARD;
                        break;

                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }


       if(motorBackRight.getCurrentPosition() < 4000){
            goFoward((float) 0.85);
            if(motorBackRight.getCurrentPosition() == 4000){
                turn((float) 0.85);
                goFoward((float) 0.85);
            }
        }
        else {
            goFoward((float)0);
        }*/
    }

    @Override
    public void stop(){
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    private void turnLeft(float motorSpeed){
        motorFrontRight.setPower(motorSpeed);
        motorBackRight.setPower(motorSpeed);
    }
    private void turnRight(float motorSpeed){
        motorFrontLeft.setPower(motorSpeed);
        motorBackLeft.setPower(motorSpeed);
    }

    private void goFoward(float motorSpeed){
        motorFrontRight.setPower(motorSpeed);
        motorFrontLeft.setPower(motorSpeed);
        motorBackRight.setPower(motorSpeed);
        motorBackLeft.setPower(motorSpeed);
    }

    private void runToPosition(){
        motorBackRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

    }

}


