package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
@Disabled
public class FishBoxAndroid extends LinearOpMode {
    private DcMotor         boxMotor   = null;
    private boolean joysticksOperational;

    @Override
    public void runOpMode() {
        boxMotor  = hardwareMap.get(DcMotor.class, "testmotor");
        while (gamepad1.a) {
            boxMotor.setPower(1);
            sleep(1000);
            boxMotor.setPower(-1);
        }

        if (gamepad1.a) {
            boxMotor.setPower(1);
        }
        else {
            boxMotor.setPower(0);
        }

        if (gamepad1.y) {
            boxMotor.setPower(-1);
        }
        else {
            boxMotor.setPower(0);
        }

        if (gamepad1.x) {
            joysticksOperational = !joysticksOperational;
        }

        if(joysticksOperational) {
            boxMotor.setPower(gamepad1.left_stick_y);
        }
    }
}