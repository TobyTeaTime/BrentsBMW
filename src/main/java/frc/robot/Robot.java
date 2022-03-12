/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Robot extends TimedRobot {
  private DifferentialDrive m_drive;
  private Joystick m_joy = new Joystick(0);
  private CANSparkMax m_leftAft;
  private CANSparkMax m_leftFront;
  private CANSparkMax m_rightAft;
  private CANSparkMax m_rightFront;

  @Override
  public void robotInit() {
  

    m_leftAft = new CANSparkMax(6, MotorType.kBrushless);
    m_leftFront = new CANSparkMax(7, MotorType.kBrushless);
    m_rightAft = new CANSparkMax(9, MotorType.kBrushless);
    m_rightFront = new CANSparkMax(8, MotorType.kBrushless);

    /**
     * The RestoreFactoryDefaults method can be used to reset the configuration parameters
     * in the SPARK MAX to their factory default state. If no argument is passed, these
     * parameters will not persist between power cycles
     */
    m_leftAft.restoreFactoryDefaults();
    m_leftFront.restoreFactoryDefaults();
    m_rightAft.restoreFactoryDefaults();
    m_rightFront.restoreFactoryDefaults();

    m_rightAft.setInverted(true);
    m_rightFront.setInverted(true);

    MotorControllerGroup m_leftGroup = new MotorControllerGroup(m_leftFront, m_leftAft);
    MotorControllerGroup m_rightGroup = new MotorControllerGroup(m_rightFront, m_rightAft);

    m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);

  
  }

  @Override
  public void teleopPeriodic() {

    m_drive.curvatureDrive(-m_joy.getY(), m_joy.getX(), false);
    
  }
}