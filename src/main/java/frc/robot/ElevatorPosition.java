package frc.robot;
/**
 * enumerator containing potential elevator position values
 */
public enum ElevatorPosition{
    /**
     * no ascertainable position (presumably between deployed and retracted)
     * 
     */
    NO, 
    /**
     * retracted
     * 
     */
    LOW, 
    /**
     * deployed
     * 
     */
    HIGH, 
    /**
     * according to the switches, the elevator is in both positions 
     * 
     */
    BOTH
}