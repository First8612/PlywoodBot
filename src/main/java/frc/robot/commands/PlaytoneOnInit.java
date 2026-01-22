package frc.robot.commands;

import com.ctre.phoenix6.controls.MusicTone;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;

public class PlaytoneOnInit extends Command {
    private final TalonFX controller1 = new TalonFX(11);
    private final TalonFX controller2 = new TalonFX(15);
    private final TalonFX controller3 = new TalonFX(13);
    private final TalonFX controller4 = new TalonFX(12);

    private final MusicTone honkTone = new MusicTone(440);
    private final MusicTone stopHonk = new MusicTone(0);
    public PlaytoneOnInit() {
        controller1.setControl(new MusicTone(261)); // C
        controller2.setControl(new MusicTone(329)); // E
        controller3.setControl(new MusicTone(392)); // G
        controller4.setControl(new MusicTone(523)); // C (octave up)
        // This command SHOULD play a tone on initialization and stops it when the command ends.

    }
    @Override
    public void initialize() {
        sendTone(honkTone);
    }

    @Override
    public void end(boolean interrupted) {
        sendTone(stopHonk);
        System.out.print("stopped");
    }

    @Override
    public boolean isFinished() {
        return true; // Command finishes immediately after initialization
    }
    private void sendTone(MusicTone tone) {
        controller1.setControl(tone);
        controller2.setControl(tone);
        controller3.setControl(tone);
        controller4.setControl(tone);
    }
}
