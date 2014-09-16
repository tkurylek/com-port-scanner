package pl.kurylek.portscanner;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.Arrays;

public class Application {

	public static void main(String[] args) {
		Arrays.asList(SerialPortList.getPortNames()).stream().map(SerialPort::new).forEach(Application::printSerialPortInfo);
	}

	private static void printSerialPortInfo(SerialPort serialPort) {
		try {
			serialPort.openPort();
			System.out.println("Port name: " + serialPort.getPortName());
			System.out.println("CTS: " + serialPort.isCTS());
			System.out.println("DSR: " + serialPort.isDSR());
			System.out.println("RING: " + serialPort.isRING());
			System.out.println("RLSD: " + serialPort.isRLSD());
			System.out.println("Opened: "+ serialPort.isOpened());
			System.out.println();
			serialPort.closePort();
		} catch (SerialPortException e) {
			throw new RuntimeException(e);
		}
	}
}
