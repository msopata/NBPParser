# NBPParser
Program which interacts with NBP API
Based on arguments:
- currency code (USD, EUR, CHF, GBP)
- start date
- end date both in YYYY-MM-DD formats

prints out basic average and standard deviation of a given period

Technologies used:
- Java
- Maven

For example run
java pl.nbpparser.MainClass CHF 2020-01-02 2020-01-31

Output would be: <br />
CHF > currency code <br />
2020-01-02 > start date  <br />
2020-01-31 > end date  <br />
3.9495425 > average rate  <br />
0.03312773 > standard deviation  <br />

