package com.qualcomm.ftcrobotcontroller.opmodes.Old;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/* Radical Change's first Autonomous Program
 * Designed for Red side of Field, Matching Goal
 */
public class RadicalChangeAuto12 extends OpMode{

    DcMotor treadRight;
    DcMotor treadLeft;
    DcMotor motor1;
    DcMotor motor2;

    static double TrackWidth = 13.75; //in inches
    static double GearRatio = 32/24; //wheel to motor
    static double wheelDiameter = 3.66; //in inches
    static double wheelCircumference = wheelDiameter * Math.PI; //Calculating Circumference
    static double ClicksPerRotation = 755.12; //declares encoder clicks per full rotation on andymark encoder
    static double CountsPerInch = ClicksPerRotation / (wheelCircumference * GearRatio); //calculates encoder clicks in one inch
    static double DegreePerInchDif = (360.0 / (2 * Math.PI)) / TrackWidth; //calculates drive difference in degrees per inch
    static double TurnDegrees1 = 45; //Degrees in first turn
    static double TurnDegrees2 = 45; //Degrees in second turn
    static double TurnDegrees3 = -45; //Degrees in third turn


    State programPosition;
    public enum State {
        START,
        FORWARD,
        TURNATLINE,
        SECONDFORWARD,
        SECONDTURN,
        THIRDFORWARD,
        PLACEHOLDER1,
        BACKWARDS,
        BACKWARDSTURN,
        FOURTHFORWARD,
        RESET,
        END,

    }
    double firstTarget = CountsPerInch * 24; //declares distance of first stretch in inches
    double secondTarget = CountsPerInch * (TurnDegrees1 / DegreePerInchDif); //creates distance of first turn in Clicks
    double thirdTarget = CountsPerInch * 34; //declares distance of second stretch in inches
    double fourthTarget = CountsPerInch * (TurnDegrees2 / DegreePerInchDif); //creates distance of second turn in Clicks
    double fifthTarget = CountsPerInch * 40; //declares distance of third stretch in inches
    double sixthTarget = -1 * (CountsPerInch * 48);
    double seventhTarget = CountsPerInch * (TurnDegrees3 / DegreePerInchDif); //creates distance of third turn in Clicks
    double eighthTarget = CountsPerInch * 49;
    public RadicalChangeAuto12() {
        programPosition = State.FORWARD;
    }

    @Override
    public void init() {

        treadLeft = hardwareMap.dcMotor.get("motorFrontRight");
        treadRight = hardwareMap.dcMotor.get("motorFrontLeft");
        motor1 = hardwareMap.dcMotor.get("motorBackRight");
        motor2 = hardwareMap.dcMotor.get("motorBackLeft");
        treadLeft.setDirection(DcMotor.Direction.REVERSE);

        programPosition = State.START;




    }
    @Override
    public void loop() {
        switch (programPosition) {

            case START:
                treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                treadLeft.setTargetPosition((int)firstTarget);
                treadRight.setTargetPosition((int)firstTarget);

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



            case FORWARD:


                treadRight.setPower(0.9);
                treadLeft.setPower(0.9);

                if(treadLeft.getCurrentPosition() <= ((int)firstTarget) - 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition = State.TURNATLINE;
                    break;
                }
            case TURNATLINE:

                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                treadRight.setTargetPosition((int)secondTarget);

                treadLeft.setPower(0);
                treadRight.setPower(0.9);

                if(treadRight.getCurrentPosition() <= ((int)secondTarget) - 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition = State.SECONDFORWARD;
                    break;
                }
            case SECONDFORWARD:


                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadLeft.setTargetPosition((int) thirdTarget);
                treadRight.setTargetPosition((int) thirdTarget);
                treadLeft.setPower(0.9);
                treadRight.setPower(0.9);

                if(treadLeft.getCurrentPosition() <= ((int)thirdTarget) - 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition = State.SECONDTURN;
                    break;
                }
            case SECONDTURN:



                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

                treadRight.setTargetPosition((int) fourthTarget);

                treadRight.setPower(0.9);

                if (treadRight.getCurrentPosition() <= ((int)fourthTarget) - 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition  = State.THIRDFORWARD;
                    break;
                }
            case THIRDFORWARD:

                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadLeft.setTargetPosition((int)fifthTarget);
                treadRight.setTargetPosition((int)fifthTarget);

                if (treadLeft.getCurrentPosition() <= ((int)fifthTarget) - 2) {
                    break;
                }
                else {
                    programPosition = State.PLACEHOLDER1;
                    break;
                }
            case PLACEHOLDER1:
                // This section is a placeholder for actions done while at the Station
                treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                programPosition = State.BACKWARDS;
                break;
            case BACKWARDS:

                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadLeft.setTargetPosition((int) sixthTarget);
                treadRight.setTargetPosition((int) sixthTarget);

                treadLeft.setPower(-0.9);
                treadRight.setPower(-0.9);

                if (treadLeft.getCurrentPosition() >= ((int)sixthTarget) + 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition = State.BACKWARDSTURN;
                    break;
                }
            case BACKWARDSTURN:

                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadLeft.setTargetPosition((int) seventhTarget);
                treadLeft.setPower(-0.9);

                if (treadLeft.getCurrentPosition() >= ((int)seventhTarget) + 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition= State.FOURTHFORWARD;
                    break;
                }
            case FOURTHFORWARD:

                treadLeft.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadRight.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
                treadLeft.setTargetPosition((int) eighthTarget);
                treadRight.setTargetPosition((int) eighthTarget);
                treadLeft.setPower(0.9);
                treadRight.setPower(0.9);

                if (treadLeft.getCurrentPosition() <= ((int)eighthTarget) - 2) {
                    break;
                }
                else {
                    treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
                    programPosition = State.RESET;
                    break;
                }
            case RESET:

                treadLeft.setPower(0);
                treadRight.setPower(0);
                programPosition = State.END;
                break;
            case END:
                break;

        }

        telemetry.addData("EncoderRight", treadRight.getCurrentPosition());
        telemetry.addData("EncoderLeft", treadRight.getCurrentPosition());
        telemetry.addData("Program State", programPosition);



    }
    @Override
    public void stop() {
        treadLeft.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        treadRight.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }




}
