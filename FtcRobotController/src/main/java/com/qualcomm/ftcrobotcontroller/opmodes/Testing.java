package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Testing extends OpMode {

    DcMotor f1;

    TouchSensor touch;

    private void b() {
        f1.setPower(-gamepad1.right_stick_y);
    }

    private void n() {
        f1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    private void i() {
        f1 = hardwareMap.dcMotor.get("f1");
        touch = hardwareMap.touchSensor.get("t1");
    }

    private void t() {
        telemetry.addData("f1p", f1.getCurrentPosition());
    }

    private void s(float p) {
        f1.setPower(p);
    }

    private void ss(float p) {
        f1.setPower(p);
        f1.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void init() {
        i(); //init components
        t(); //ads telemetry
    }

    Date timer = new Date();


    @Override
    public void loop() {
        t(); //updates telemetry
        b(); //moves motors
        if (touch.isPressed()) {
            s(0.5f);
        } else if(!touch.isPressed()){
            ss(0.6f);
        }
    }

    @Override
    public void stop() {
        s(0); //sets motor power to 0
        n(); //Resets Encoders
    }
}
