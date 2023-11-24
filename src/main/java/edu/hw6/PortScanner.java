package edu.hw6;

import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PortScanner {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static String HEADER = "Протокол  Порт   Используется  Сервис";
    private final static int MAX_PORT = 49151;
    private final static Map<Integer, String> KNOWN_PORTS = Map.of(
        135, "EPMAP",
        137, "Служба имен NetBIOS",
        139, "Служба сеансов NetBIOS",
        445, "Microsoft-DS Active Directory",
        843, "Adobe Flash",
        1900, "Simple Service Discovery Protocol (SSDP)",
        3702, "Динамическое обнаружение веб-служб",
        5353, "Многоадресный DNS",
        17500, "Dropbox",
        27017, "MongoDB"
    );

    private enum Protocol {
        TCP, UDP
    }

    private PortScanner() {
    }

    public static boolean isTcpPortUsed(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static void scanTcpPorts() {
        for (int port = 0; port <= MAX_PORT; port++) {
            boolean isUsed = isTcpPortUsed(port);
            tracePort(Protocol.TCP, port, isUsed);
        }
    }

    public static boolean isUdpPortUsed(int port) {
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static void scanUdpPorts() {
        LOGGER.trace(HEADER);
        for (int port = 0; port <= MAX_PORT; port++) {
            boolean isUsed = isUdpPortUsed(port);
            tracePort(Protocol.UDP, port, isUsed);
        }
    }

    private static void tracePort(Protocol protocol, int port, boolean isUsed) {
        String process = KNOWN_PORTS.getOrDefault(port, "");

        LOGGER.trace(String.format(
            "%-4s  %-5d  %-12s  %-30s",
            protocol.toString(),
            port,
            isUsed ? "Да" : "Нет",
            process
        ));
    }
}
