package com.qualcomm.ftcrobotcontroller.opmodes.Old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Nolan on 12/9/2015.
 */
public class Auto extends OpMode {

    static int wheelDiameter = 10;
    static int wheelCircumference = (int) (wheelDiameter * Math.PI);
    static int clicksPerRotation = 1120;

    //Motors
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;

    //Servos
    Servo leftArm;
    Servo rightArm;

    int stopRobotPower = 0;

    private void initComponents() {
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

        //Servos
        leftArm = hardwareMap.servo.get("servoLeftArm");
        rightArm = hardwareMap.servo.get("servoRightArm");
    }

    private void stopRobot() {
        motorBackRight.setPower(stopRobotPower);
        motorBackLeft.setPower(stopRobotPower);
        motorFrontRight.setPower(stopRobotPower);
        motorFrontLeft.setPower(stopRobotPower);
    }

    private void resetEncoders() {
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    private void runToPos() {
        motorFrontRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorFrontLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        motorBackLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
    }

    State programState;

    public enum State {
        START,
        FORWARD,
        RESET,
        END,
    }

    @Override
    public void stop() {
        stopRobot();
    }

    @Override
    public void init() {
        initComponents();
    }

    @Override
    public void loop() {
        switch (programState) {
            case START:
                resetEncoders();
                runToPos();
                if (motorFrontRight.getCurrentPosition() == 0) {
                    if (motorFrontLeft.getCurrentPosition() == 0) {
                        if (motorBackRight.getCurrentPosition() == 0) {
                            if (motorBackLeft.getCurrentPosition() == 0) {
                                programState = State.FORWARD;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
                programState = State.FORWARD;
                break;
            case FORWARD:

            case RESET:
                resetEncoders();
                programState = State.END;
                break;
            case END:
                stopRobot();
                break;
        }
    }

}
