// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveRobotCentricCommand;
import frc.robot.commands.DriveTankCommand;
import frc.robot.commands.DriveFieldCentricCommand;
import frc.robot.commands.HonkCommand;
import frc.robot.subsystems.Drivebase;

public class RobotContainer {
  private Drivebase drivebase = new Drivebase();
  private CommandXboxController controller = new CommandXboxController(0);
  private Command[] driveCommands = new Command[] {
    new DriveFieldCentricCommand(drivebase, controller),
    new DriveRobotCentricCommand(drivebase, controller),
    new DriveTankCommand(drivebase, controller)
  };
  private int driveModeIndex = 0;

  public RobotContainer() {
    configureBindings();

    SmartDashboard.putData(drivebase);
  }

  private void configureBindings() {
    controller.leftStick().whileTrue(new HonkCommand());

    controller.b().onTrue(Commands.runOnce(() -> {
      driveModeIndex++;
      if (driveModeIndex > driveCommands.length - 1) {
        driveModeIndex = 0;
      }

      drivebase.setDefaultCommand(driveCommands[driveModeIndex]);
    }, drivebase));

    drivebase.setDefaultCommand(driveCommands[driveModeIndex]);
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
