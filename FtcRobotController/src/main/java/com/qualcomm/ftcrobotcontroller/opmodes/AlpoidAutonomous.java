package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.Date;

public class AlpoidAutonomous extends OpMode {


    DcMotor f1;
    DcMotor f2;
    DcMotor b3;
    DcMotor b4;
    DcMotor t2;

    @Override
    public void init() {
        i(); //init components
        t(); //ads telemetry
    }

    @Override
    public void loop() {
        Date timer = new Date();
        if(timer.getTime() > 4000){
            s(0.85f);
        } else {
            s(0.0f);
        }
    }

    @Override
    public void stop() {
        s(0); //sets motor power to 0
    }

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

    private void i() {
        f1 = hardwareMap.dcMotor.get("f1");
        f2 = hardwareMap.dcMotor.get("f2");
        b3 = hardwareMap.dcMotor.get("b3");
        b4 = hardwareMap.dcMotor.get("b4");
        t2 = hardwareMap.dcMotor.get("t2");
    }

    private void s(float p) {
        f1.setPower(p);
        f2.setPower(p);
        b3.setPower(p);
        b4.setPower(p);
        t2.setPower(p);
    }

    private void t() {
        telemetry.addData("f1p", f1.getCurrentPosition());
        telemetry.addData("f2p", f2.getCurrentPosition());
        telemetry.addData("f3p", b3.getCurrentPosition());
        telemetry.addData("f4p", b4.getCurrentPosition());
        telemetry.addData("t2p", t2.getCurrentPosition());
    }
}
