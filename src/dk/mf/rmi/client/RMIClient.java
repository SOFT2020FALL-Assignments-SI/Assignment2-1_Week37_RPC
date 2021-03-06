package dk.mf.rmi.client;
/**
 * This is a simple client program that uses methods as a service from another, server program.
 * <p>
 * There are two services provided by the server and consumed by this client:
 * - calculator
 * - date/time info
 * <p>
 * The client gets input from the console: two operands [numbers] and arithmetic operator [character]
 * Then the client builds a stub, which first searches for a service and then connects with the server for getting the service
 * Finally, the client prints out the results and the current date on the console
 *
 * @author Dora Di
 */

import dk.mf.rmi.server.*;

import java.rmi.Naming;
import java.util.Scanner;

public class RMIClient {
    private static int a;
    private static int b;
    private static char op;
    public static final String OPS = "+-*/%";

    public static void getInput() {
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter two integer numbers: ");
        a = inp.nextInt();
        b = inp.nextInt();
        do {
            System.out.println("Enter operator: ");
            op = inp.next().charAt(0);
        }
        while (OPS.indexOf(op) == -1);
    }

    public static void getService() throws Exception {
        double result;
        java.util.Date today;

        // Lookup in the registry for the service interface you know by name
        RMIInterface obj = (RMIInterface) Naming.lookup("//localhost/Compute");

        // Send requests, get responses
        result = obj.calculate(a, b, op);
        today = obj.getDate();

        printout(result, today);
    }

    public static void printout(double result, java.util.Date today) {
        // Print the results on the Client console
        System.out.println("Client: result = " + result);
        System.out.println("Client: today is " + today);
    }


    public static void main(String[] args) {
        try {
            getInput();
            getService();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }
}
