package org.firstinspires.ftc.teamcode.backups;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Competition Controls Backup", group="Linear OpMode")
@Disabled
public class Competition_Controls_Backup extends LinearOpMode {
    private DcMotor front_motor_right;
    private DcMotor front_motor_left;
    private DcMotor back_motor_right;
    private DcMotor back_motor_left;
//    private Servo leftBaseServo;
//    private Servo rightBaseServo;
//    private Servo elbow;
//    private Servo wrist;
//    private Servo clawLeft;
//    private Servo clawRight;

    @Override
    public void runOpMode() throws InterruptedException {

        double speed = 0.5;

        /*

        rightBaseServo = hardwareMap.get(Servo.class, "rightBaseServo");
        leftBaseServo = hardwareMap.get(Servo.class,"leftBaseServo");
        elbow = hardwareMap.get(Servo.class, "elbow");
        wrist = hardwareMap.get(Servo.class, "wrist");
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        waitForStart();
        */

        front_motor_left = hardwareMap.get(DcMotor.class, "front motor left");
        front_motor_right = hardwareMap.get(DcMotor.class, "front motor right");
        back_motor_left = hardwareMap.get(DcMotor.class, "back motor left");
        back_motor_right = hardwareMap.get(DcMotor.class, "back motor right");

        waitForStart();
        while (opModeIsActive()) {
            /*
            double armSpeed = 0.0005;
            if (gamepad2.a) {
                leftBaseServo.setPosition(leftBaseServo.getPosition() + armSpeed * 3);
                rightBaseServo.setPosition(rightBaseServo.getPosition() - armSpeed * 3);
            }
            else if (gamepad2.b) {
                leftBaseServo.setPosition(leftBaseServo.getPosition() - armSpeed * 3);
                rightBaseServo.setPosition(rightBaseServo.getPosition() + armSpeed * 3);
            }

            if (gamepad2.dpad_up) {
                elbow.setPosition(elbow.getPosition() + armSpeed / 2);
            }
            else if (gamepad2.dpad_down) {
                elbow.setPosition(elbow.getPosition() - armSpeed / 2);
            }

            if (gamepad2.dpad_right) {
                wrist.setPosition(wrist.getPosition() + armSpeed);
            }
            else if (gamepad2.dpad_left) {
                wrist.setPosition(wrist.getPosition() - armSpeed);
            }

            if (gamepad2.left_trigger > 0) {
                clawLeft.setPosition(clawLeft.getPosition() + armSpeed);
            }
            else if (gamepad2.left_bumper) {
                clawLeft.setPosition(clawLeft.getPosition() - armSpeed);
            }

            if (gamepad2.right_trigger > 0) {
                clawRight.setPosition(clawRight.getPosition() + armSpeed);
            }
            else if (gamepad2.right_bumper) {
                clawRight.setPosition(clawRight.getPosition() + armSpeed);
            }

             */

            if (gamepad1.dpad_down) {
                front_motor_left.setDirection(DcMotor.Direction.REVERSE);
                front_motor_right.setDirection(DcMotor.Direction.FORWARD);
                back_motor_left.setDirection(DcMotor.Direction.FORWARD);
                back_motor_right.setDirection(DcMotor.Direction.REVERSE);
                front_motor_left.setPower(speed);
                front_motor_right.setPower(speed);
                back_motor_left.setPower(speed);
                back_motor_right.setPower(speed);

            }
            else if (gamepad1.dpad_up) {
                front_motor_left.setDirection(DcMotor.Direction.FORWARD);
                front_motor_right.setDirection(DcMotor.Direction.REVERSE);
                back_motor_left.setDirection(DcMotor.Direction.REVERSE);
                back_motor_right.setDirection(DcMotor.Direction.FORWARD);
                front_motor_left.setPower(speed);
                front_motor_right.setPower(speed);
                back_motor_left.setPower(speed);
                back_motor_right.setPower(speed);
            }
            else if (gamepad1.dpad_right) {
                front_motor_left.setDirection(DcMotor.Direction.REVERSE);
                front_motor_right.setDirection(DcMotor.Direction.REVERSE);
                back_motor_left.setDirection(DcMotor.Direction.FORWARD);
                back_motor_right.setDirection(DcMotor.Direction.FORWARD);
                front_motor_left.setPower(speed);
                front_motor_right.setPower(speed);
                back_motor_left.setPower(speed);
                back_motor_right.setPower(speed);
            }
            else if (gamepad1.dpad_left) {
                front_motor_left.setDirection(DcMotor.Direction.FORWARD);
                front_motor_right.setDirection(DcMotor.Direction.FORWARD);
                back_motor_left.setDirection(DcMotor.Direction.REVERSE);
                back_motor_right.setDirection(DcMotor.Direction.REVERSE);
                front_motor_left.setPower(speed);
                front_motor_right.setPower(speed);
                back_motor_left.setPower(speed);
                back_motor_right.setPower(speed);
            }
            else if (gamepad1.x) {
                //ROTATE:
                front_motor_left.setDirection(DcMotor.Direction.FORWARD);
                front_motor_right.setDirection(DcMotor.Direction.FORWARD);
                back_motor_left.setDirection(DcMotor.Direction.FORWARD);
                back_motor_right.setDirection(DcMotor.Direction.FORWARD);
                front_motor_left.setPower(speed/2);
                front_motor_right.setPower(speed/2);
                back_motor_left.setPower(speed/2);
                back_motor_right.setPower(speed/2);
            }
            else if (gamepad1.b) {
                front_motor_left.setDirection(DcMotor.Direction.REVERSE);
                front_motor_right.setDirection(DcMotor.Direction.REVERSE);
                back_motor_left.setDirection(DcMotor.Direction.REVERSE);
                back_motor_right.setDirection(DcMotor.Direction.REVERSE);
                front_motor_left.setPower(speed/2);
                front_motor_right.setPower(speed/2);
                back_motor_left.setPower(speed/2);
                back_motor_right.setPower(speed/2);
            }
            else {
                front_motor_left.setPower(0);
                front_motor_right.setPower(0);
                back_motor_left.setPower(0);
                back_motor_right.setPower(0);
            }

            if (gamepad1.a){
                speed = 0.6;
            }
            if (gamepad1.y) {
                speed = 0.35;
            }
            /*telemetry.addData("base servo position: ", leftBaseServo.getPosition());
            telemetry.addData("elbow position: ", elbow.getPosition());
            telemetry.addData("wrist position: ", wrist.getPosition());
            telemetry.addData("right_claw data: ", clawRight.getPosition());
            telemetry.addData("left claw data : ", clawLeft.getPosition());
            */telemetry.addData("Current motor speed: ", speed);
            telemetry.update();
        }

    }
}