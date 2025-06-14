// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivebase;

public class RobotContainer {
  private Drivebase drivebase = new Drivebase();
  private CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();

    drivebase.setDefaultCommand(Commands.run(() -> {
      var xSpeed = controller.getLeftX() * Drivebase.kMaxSpeed;
      var ySpeed = controller.getLeftY() * Drivebase.kMaxSpeed;
      var rotSpeed = controller.getRightX() * Drivebase.kMaxAngularSpeed;

      drivebase.drive(xSpeed, ySpeed, rotSpeed);
    }, drivebase));
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
