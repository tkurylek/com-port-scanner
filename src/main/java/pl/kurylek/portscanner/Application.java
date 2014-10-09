package pl.kurylek.portscanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    Arrays.asList(SerialPortList.getPortNames())
        .stream().map(SerialPort::new).forEach(Application::printSerialPortInfo);
  }

  private static void printSerialPortInfo(SerialPort serialPort) {
    try {
      serialPort.openPort();
      LOGGER.info(new StringBuilder()
          .append("Port name: ").append(serialPort.getPortName())
          .append("\nOpened: ").append(serialPort.isOpened())
          .append("\nClear to Send: ").append(serialPort.isCTS())
          .append("\nData Set Ready: ").append(serialPort.isDSR())
          .append("\nReceive Line Signal Detect: ").append(serialPort.isRLSD())
          .append("\nRing Indicator: ").append(serialPort.isRING())
          .append("\nFlow control mode: ").append(serialPort.getFlowControlMode())
          .toString());
      serialPort.closePort();
    } catch (SerialPortException e) {
      LOGGER.error("Communication failed!", e);
    }
  }
}
