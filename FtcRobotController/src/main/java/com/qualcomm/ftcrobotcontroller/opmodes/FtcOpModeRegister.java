package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.eventloop.opmode.OpModeRegister;

public class FtcOpModeRegister implements OpModeRegister {

    public void register(OpModeManager manager) {

        manager.register("AlpoidTeleOp", AlpoidTeleOp.class);
        manager.register("AlpoidAutonomou", AlpoidAutonomous.class);
        manager.register("Testing", Testing.class);

    }
}
