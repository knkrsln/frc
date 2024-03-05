// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private CANSparkMax m_left_followMotor;
  private CANSparkMax m_left_leadMotor;
  private CANSparkMax m_right_followMotor;
  private CANSparkMax m_right_leadMotor;
  private CANSparkMax m_climb;
  private static DifferentialDrive m_robotDrive;
  private final Joystick m_stick = new Joystick(0);

  @Override

  public void robotInit() {
    m_left_leadMotor = new CANSparkMax(1, MotorType.kBrushed);
    m_left_followMotor = new CANSparkMax(2, MotorType.kBrushed);
    m_right_leadMotor = new CANSparkMax(3, MotorType.kBrushed);
    m_right_followMotor = new CANSparkMax(4, MotorType.kBrushed);
    m_climb = new CANSparkMax(5, MotorType.kBrushless);
    
    m_left_leadMotor.restoreFactoryDefaults();
    m_left_followMotor.restoreFactoryDefaults();

    m_right_leadMotor.restoreFactoryDefaults();
    m_right_followMotor.restoreFactoryDefaults();

    m_left_followMotor.follow(m_left_leadMotor);
    m_right_followMotor.follow(m_right_leadMotor);

    m_robotDrive = new DifferentialDrive(m_left_leadMotor, m_right_leadMotor);

    m_right_leadMotor.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    m_robotDrive.arcadeDrive(-m_stick.getY(), -m_stick.getX());
    if (m_stick.getRawButtonPressed(0)){
      m_climb.set(0.5);

    }
    else if (m_stick.getRawButtonPressed(1)){
      m_climb.set(-0.5);
    }
    else {
      m_climb.set(0);
    }
  }
}
