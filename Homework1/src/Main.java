import utils.Values;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static Values CountWACC(
            double div,
            double priceEquity,
            double growth,
            double capitalSC,
            double capitalBC,
            double priceBC,
            double tax
    ) {
        double priceSC = div * (1 + growth) / (priceEquity - div);
        double sumCapital = capitalSC + capitalBC;
        double capitalPercentBC = capitalBC / sumCapital;
        double capitalPercentSC = capitalSC / sumCapital;
        double costBC = priceBC * (1 - tax);

        double WACC = (costBC * capitalPercentBC + priceSC * capitalPercentSC) * 100;

        return new Values(costBC * 100, priceBC * 100, WACC, priceSC * 100);
    };

    static double round(double number) {
        return (double)Math.round(number * 100) / 100;
    }

    public static void main(String[] args) {
        double div = 0.25;
        double priceEquity = 2.9;
        double growth = 0.04;
        double capitalSC = 62;
        double capitalBC = 31;
        double priceBC = 0.08;
        double tax = 0.25;

        Values values = CountWACC(div,
                priceEquity,
                growth,
                capitalSC,
                capitalBC,
                priceBC,
                tax);

        double costBC = values.costBC;
        double WACC = values.WACC;
        double priceSC = values.priceSC;;


        JFrame window = new JFrame("Оценка рентабельности производства");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(50, 50, 325, 300);
        window.setLayout(null);

        JLabel lCostBC = new JLabel("Cost BC: " + round(costBC));
        lCostBC.setBounds(205, 5, 100, 20);
        window.add(lCostBC);

        JLabel lPriceBC = new JLabel("Price BC: " + round(priceBC));
        lPriceBC.setBounds(205, 25, 100, 20);
        window.add(lPriceBC);

        JLabel lWACCValue = new JLabel("WACC: " + round(WACC));
        lWACCValue.setBounds(205, 45, 100, 20);
        window.add(lWACCValue);

        JLabel lPriceSc = new JLabel("Price SC: " + round(priceSC));
        lPriceSc.setBounds(205, 65, 100, 20);
        window.add(lPriceSc);

        JLabel lVATValue = new JLabel("VAT value");
        lVATValue.setBounds(5, 5, 100, 20);
        window.add(lVATValue);
        JTextField VAT = new JTextField();
        VAT.setBounds(105, 5, 50, 20);
        window.add(VAT);

        JLabel lStockPrice = new JLabel("Stock Price value");
        lStockPrice.setBounds(5, 30, 100, 20);
        window.add(lStockPrice);
        JTextField StockPrice = new JTextField();
        StockPrice.setBounds(105, 30, 50, 20);
        window.add(StockPrice);

        JButton countPrice = new JButton("Рассчёт рыночной стоимости");
        countPrice.setBounds(5, 100, 300, 20);
        window.add(countPrice);

        JLabel lPrice = new JLabel("Результат расчёта РС предприятия");
        lPrice.setBounds(5, 125, 300, 20);
        window.add(lPrice);
        JLabel lPriceValue = new JLabel("");
        lPriceValue.setBounds(5, 155, 300, 20);
        window.add(lPriceValue);

        JButton plotResTax = new JButton("Зависимость WACC от налога на прибыль");
        plotResTax.setBounds(5, 185, 300, 20);
        window.add(plotResTax);

        JButton getBarCode = new JButton("Генерация штрих-кода");
        getBarCode.setBounds(5, 215, 300, 20);
        window.add(getBarCode);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double Benefit = 3500, LoanCapitalPrice = 7000, StockPriceValue, VATValue, MarketPrice;
                StockPriceValue = Double.parseDouble(StockPrice.getText());
                VATValue = Double.parseDouble(VAT.getText());
                MarketPrice = (Benefit * (1 - VATValue) / StockPriceValue) + VATValue + LoanCapitalPrice;
                lPriceValue.setText(round(MarketPrice) + "");
            }
        };

        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("python tax_wacc.py");
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        };

        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Runtime.getRuntime().exec("python my_barcode.py");
                }
                catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
        };

        countPrice.addActionListener(actionListener);
        plotResTax.addActionListener(actionListener1);
        getBarCode.addActionListener(actionListener2);
        window.setVisible(true);
    }
}
