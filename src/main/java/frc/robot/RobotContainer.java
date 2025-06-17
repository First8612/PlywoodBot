// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.HonkCommand;
import frc.robot.subsystems.Drivebase;

public class RobotContainer {
  private Drivebase drivebase = new Drivebase();
  private CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();

    drivebase.setDefaultCommand(Commands.run(() -> {
      var ySpeed = MathUtil.applyDeadband(-controller.getLeftX(), 0.1) * Drivebase.kMaxSpeed;
      var xSpeed = MathUtil.applyDeadband(-controller.getLeftY(), 0.1) * Drivebase.kMaxSpeed;
      var rotSpeed = controller.getRightX() * Drivebase.kMaxAngularSpeed;

      drivebase.drive(xSpeed, ySpeed, rotSpeed);
    }, drivebase));
  }

  private void configureBindings() {
    controller.leftStick().whileTrue(new HonkCommand());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
