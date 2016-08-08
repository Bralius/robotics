package com.qualcomm.ftcrobotcontroller.opmodes.Alpoid;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class AlpoidUtils extends OpMode{

    //Motor
    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackLeft;
    DcMotor motorBackRight;
    DcMotor motorTop1;
    DcMotor motorTop2;

    //Servo
    Servo leftArm;
    Servo rightArm;
    Servo topArm;
    Servo servoFront;

    //Servo utils
    final static double ARM_MIN_RANGE = 0.20;
    final static double ARM_MAX_RANGE = 0.90;
    //Position
    double armPosition;
    double armPosition1;
    double lPos;
    double servoFrontPosition;
    //Delta
    double armDelta = 0.1;
    double armDelta1 = 0.1;
    double lD = 0.1;
    double frontDelta = 0.1;

    int stopRobotPower = 0;

    public void stopRobot() {
        motorBackRight.setPower(stopRobotPower);
        motorBackLeft.setPower(stopRobotPower);
        motorFrontRight.setPower(stopRobotPower);
        motorFrontLeft.setPower(stopRobotPower);
    }

    public void initComp(){
            motorFrontLeft = hardwareMap.dcMotor.get("f1");
            motorFrontRight = hardwareMap.dcMotor.get("f2");
            motorBackLeft = hardwareMap.dcMotor.get("b3");
            motorBackRight = hardwareMap.dcMotor.get("b4");
       //     motorTop1 = hardwareMap.dcMotor.get("motorTop1");
            motorTop2 = hardwareMap.dcMotor.get("t2");

            motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
            motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
            motorTop1.setDirection(DcMotor.Direction.REVERSE);
            motorTop2.setDirection(DcMotor.Direction.REVERSE);

            //Servos
            leftArm = hardwareMap.servo.get("fs1");
            rightArm = hardwareMap.servo.get("ss1");
  //          topArm = hardwareMap.servo.get("servoTopArm");
//            servoFront = hardwareMap.servo.get("servoFront");
    }

    @Override
    public void init() {
        initComp();
    }

    private void addTelementary(){
        telemetry.addData("Top Servo Pos", topArm.getPosition());
        telemetry.addData("Motor Top 1", motorTop1.getCurrentPosition());
        telemetry.addData("Motor Top 2", motorTop2.getCurrentPosition());
        telemetry.addData("Servo Front", servoFront.getPosition());
        telemetry.addData("Top Servo Pos", topArm.getPosition());
        telemetry.addData("Motor Front Right", motorFrontRight.getCurrentPosition());
        telemetry.addData("Motor Back Right", motorBackRight.getCurrentPosition());
        telemetry.addData("Motor Front Left", motorFrontLeft.getCurrentPosition());
        telemetry.addData("Motor Back Left", motorBackLeft.getCurrentPosition());
        telemetry.addData("Bucket Raise Position", armPosition);
    }

    @Override
    public void stop(){
        stopRobot();
    }

    @Override
    public void loop() {
        addTelementary();
        armPosition = Range.clip(armPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);
        armPosition1 = Range.clip(armPosition1, ARM_MIN_RANGE, ARM_MAX_RANGE);
        lPos = Range.clip(lPos, ARM_MIN_RANGE, ARM_MAX_RANGE);
        topArm.setPosition(topArm.getPosition());
        servoFrontPosition = Range.clip(servoFrontPosition, ARM_MIN_RANGE, ARM_MAX_RANGE);

        //Front Motors
        motorFrontRight.setPower(-gamepad1.right_stick_y);
        motorFrontLeft.setPower(-gamepad1.left_stick_y);

        //Back Motors
        motorBackRight.setPower(-gamepad1.right_stick_y);
        motorBackLeft.setPower(-gamepad1.left_stick_y);

        if (gamepad2.dpad_up){
            armPosition += armDelta;
        }
        if(gamepad2.dpad_down) {
            armPosition -= armDelta;
        }
        if(gamepad2.dpad_left){
            armPosition1 += armDelta1;
        }
        if(gamepad2.dpad_right){
            armPosition1 -= armDelta1;
        }

        if(gamepad2.x){
            servoFrontPosition += frontDelta;
        }
        if(gamepad2.b){
            servoFrontPosition -= frontDelta;
        }


        if(gamepad1.a){
            int a = 0;
        }

        if(gamepad1.dpad_up){
            lPos += lD;
        }
        if(gamepad1.dpad_down){
            lPos -= lD;
        }
        motorTop1.setPower(-gamepad2.right_stick_y);
        motorTop2.setPower(-gamepad2.left_stick_y);

        leftArm.setPosition(armPosition);
        rightArm.setPosition(armPosition1);
    }
}
