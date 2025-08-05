package frc.robot.commands;

import static edu.wpi.first.units.Units.Rotation;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.Drivebase;

public class MagicYeet extends Command {
    private Drivebase drivebase;
    private PIDController rotPidController = new PIDController(0.02, 0, 0);
    private PIDController strafePidController = new PIDController(0.3, 0, 0);

    public MagicYeet (Drivebase drivebase) {
        super();
        this.drivebase = drivebase;
        SmartDashboard.putData("AprilTagSquareUp/RotPID", rotPidController);
        SmartDashboard.putData("AimTowardAprilTag/RotPID", rotPidController);

    }

    @Override
    public void initialize() {
        rotPidController.reset();
    }
 @Override
    public void execute() {
        super.execute();

        var limelightName = "limelight-right";
        if (LimelightHelpers.getFiducialID(limelightName) > -1) {
            var targetPose = LimelightHelpers.getTargetPose3d_RobotSpace("limelight-right");
            var tx = LimelightHelpers.getTX("limelight-right");
            var targetRotation = targetPose.getRotation().getMeasureY().magnitude();
            var rot = rotPidController.calculate(tx);
            var strafe = strafePidController.calculate(targetRotation);
            SmartDashboard.putNumber("AprilTagSquareUp/targetRotation", targetRotation);
            SmartDashboard.putNumber("AprilTagSquareUp/strafe", strafe);
            SmartDashboard.putNumber("AimTowardAprilTag/rot", rot);
            drivebase.drive(0, -strafe, rot);
        }

    }
    
} 

