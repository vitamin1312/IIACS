from matplotlib import pyplot as plt

def CountWACC(
             div,
             priceEquity,
             growth,
             capitalSC,
             capitalBC,
             priceBC,
             tax
    ):
    priceSC = div * (1 + growth) / (priceEquity - div)
    sumCapital = capitalSC + capitalBC
    capitalPercentBC = capitalBC / sumCapital
    capitalPercentSC = capitalSC / sumCapital
    costBC = priceBC * (1 - tax)

    WACC = (costBC * capitalPercentBC + priceSC * capitalPercentSC) * 100

    return WACC

div = 0.25
priceEquity = 2.9
growth = 0.04
capitalSC = 62
capitalBC = 31
priceBC = 0.08
tax = 0.25

WACC = list()
tax = list()

for tax_per in range(0, 100):
    t = tax_per / 100
    tax.append(t)
    WACC.append(CountWACC(
                    div,
                    priceEquity,
                    growth,
                    capitalSC,
                    capitalBC,
                    priceBC,
                    t
    ))

plt.plot(tax, WACC)
plt.grid()
plt.show()
