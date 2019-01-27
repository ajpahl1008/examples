package com.pahlsoft.utilities.runner;

import com.pahlsoft.utilities.engine.*;
import com.pahlsoft.utilities.threader.AccountGeneratorEngineThreader;
import com.pahlsoft.utilities.threader.TransactionEngineThreader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EngineRunner {
    static Logger log = LoggerFactory.getLogger(EngineRunner.class);

    public static void main(String[] args) {
        validateArguments(args);
        try {
            switch (args[1]) {
                case "fraud" :
                    TransactionEngineThreader.runEngine(1,new FradulentCreditTransactionEngine());
                    TransactionEngineThreader.runEngine(1,new FradulentDebitTransactionEngine());
                    break;
                case "debit":
                    TransactionEngineThreader.runEngine(Integer.parseInt(args[0]),new DebitCardTransactionEngine());
                    TransactionEngineThreader.runEngine(1,new FradulentDebitTransactionEngine());
                    break;
                case "credit":
                    TransactionEngineThreader.runEngine(Integer.parseInt(args[0]),new CreditCardTransactionEngine());
                    TransactionEngineThreader.runEngine(1,new FradulentCreditTransactionEngine());
                    break;
                case "both":
                    TransactionEngineThreader.runEngine(Integer.parseInt(args[0]),new DebitCardTransactionEngine());
                    TransactionEngineThreader.runEngine(Integer.parseInt(args[0]),new CreditCardTransactionEngine());
                    TransactionEngineThreader.runEngine(1,new FradulentCreditTransactionEngine());
                    TransactionEngineThreader.runEngine(1,new FradulentDebitTransactionEngine());
                    break;
                case "dda":
                    AccountGeneratorEngineThreader.runEngine(Integer.parseInt(args[0]),new AccountGeneratorEngine());
                    break;
            }

        } catch (Exception e) {
            if (log.isDebugEnabled()) {e.printStackTrace();}
        }
    }

    private static void validateArguments(String[] args) {
        if (args.length !=2 ) {
            log.error("Error - Improper Usage, try: EngineRunner.jar [number_of_threads] [debit|debit|both|dda] ");
            System.exit(1);
        }
    }
}
