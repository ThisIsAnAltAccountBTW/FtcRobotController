/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")

public class controls extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
//    private DcMotor leftDrive = null;
//    private DcMotor rightDrive = null;

    private Servo lowArmRight;
    private Servo lowArmLeft;
    private Servo midArm;
    private Servo highArm;

    @Override
    public void runOpMode() {
        //telemetry initialization
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //hardware initialization
        lowArmRight  = hardwareMap.get(Servo.class, "low arm right");
        lowArmLeft  = hardwareMap.get(Servo.class, "low arm left");
        midArm = hardwareMap.get(Servo.class, "mid arm");
        highArm = hardwareMap.get(Servo.class, "high arm");
        //Default forward direction set on all servos
        lowArmRight.setDirection(Servo.Direction.FORWARD);
        lowArmLeft.setDirection(Servo.Direction.FORWARD);
        midArm.setDirection(Servo.Direction.FORWARD);
        highArm.setDirection(Servo.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        //vars for telemetry
        double drive = 0;
        double turn = 0;
        boolean midMoveDown = false;
        boolean midMoveUp = false;
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            //FUNCTION CALL
            MoveArm();
            // TELEMETRY
            //runtime
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //sticks, left and right
            telemetry.addData("Stick Left:","Vector: "+Math.abs(drive));
            telemetry.addData("Stick Right:","Vector: "+Math.abs(turn));
            //Dpad, up and down
            telemetry.addData("DPAD","Direction: Up = "+gamepad1.dpad_up);
            telemetry.addData("DPAD","Direction: Down = "+gamepad1.dpad_down);
            //gamepad1 a
            telemetry.addData("Controller1 a", "Is pressed: " + gamepad1.a);
            telemetry.update();
        }

    }
    double drive = 0;
    double turn = 0;
    boolean midMoveDown = false;
    boolean midMoveUp = false;
    double sensitivityMultiplier = 1.0;
    boolean aPressed = false;
    boolean bPressed = false;
    void MoveArm() {
        //vars: drive = vert left stick move, turn = horiz right stick move
        drive = drive-gamepad1.left_stick_y/(500 * sensitivityMultiplier);
        turn  =  turn+gamepad1.right_stick_x/(500 * sensitivityMultiplier);
        //Dpad up and down for mid arm movements
        midMoveDown = gamepad1.dpad_down;
        midMoveUp = gamepad1.dpad_up;
        //Mid Arm Movements(DPAD UP AND DPAD DOWN)
        if(midMoveDown && !midMoveUp){
            midArm.setPosition(midArm.getPosition()-0.05);
        }
        if(midMoveUp && !midMoveDown){
            midArm.setPosition(midArm.getPosition()+0.05);
        }
        if(gamepad1.start) {
            sensitivityMultiplier = 2.0;
        }
        if(gamepad1.back) {
            sensitivityMultiplier = 1.0;
        }
        //Low Arm Movements(A and B)
        if(aPressed && !bPressed){
            lowArmRight.setPosition(lowArmRight.getPosition()+0.05);
        }
        else if(bPressed && !aPressed){
            lowArmRight.setPosition(lowArmRight.getPosition()-0.05);
        }
        if(gamepad1.x){
            lowArmLeft.setPosition(lowArmLeft.getPosition()-0.05);
        }
        if(gamepad1.y){
            lowArmLeft.setPosition(lowArmLeft.getPosition()-0.05);
        }
        // drive = left stick vertical movement
        // Check value of drive and move servo accordingly
        if(drive <0){
            drive = Math.abs(drive);
            midArm.setDirection(Servo.Direction.REVERSE);
            midArm.setPosition(drive);
        }
        if (drive == 0){
            midArm.setDirection(Servo.Direction.FORWARD);
        }
        else {
            midArm.setDirection(Servo.Direction.FORWARD);
            midArm.setPosition(drive);
        }
    }
}
