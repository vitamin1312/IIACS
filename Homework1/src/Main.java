import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.Chart.ChartFactory;
import org.jfree.Chart.ChartPanel;
import org.jfree.Chart.


public class Main {

    public static double CountWACC(
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

        // Лучше бы я не исполльзовал принты...
        System.out.println("costBC: " + costBC * 100);
        System.out.println("priceBC: " + priceBC * 100);
        System.out.println(WACC);
        System.out.println("priceSC: " + priceSC * 100);

        return WACC;
    };

    public static void main(String[] args) {
        double div = 0.25;
        double priceEquity = 2.9;
        double growth = 0.04;
        double capitalSC = 62;
        double capitalBC = 31;
        double priceBC = 0.08;
        double tax = 0.25;

        double WACC = CountWACC(div,
                priceEquity,
                growth,
                capitalSC,
                capitalBC,
                priceBC,
                tax);

        JFrame window = new JFrame("Оценка рентабельности производства");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(50, 50, 325, 250);
        window.setLayout(null);

        JLabel lWACCValue = new JLabel("WACC: " + WACC);
        lWACCValue.setBounds(205, 5, 100, 20);
        window.add(lWACCValue);

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

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double Benefit = 3500, LoanCapitalPrice = 7000, StockPriceValue, VATValue, MarketPrice;
                StockPriceValue = Double.parseDouble(StockPrice.getText());
                VATValue = Double.parseDouble(VAT.getText());
                MarketPrice = (Benefit * (1 - VATValue) / StockPriceValue) + VATValue + LoanCapitalPrice;
                lPriceValue.setText(MarketPrice + "");
            }
        };

        countPrice.addActionListener(actionListener);
        window.setVisible(true);
    }
}
