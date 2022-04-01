# The Investment Portfolio

## How will you spend your money? 

This application is made for anyone who wants an organized portfolio that displays all the investments they made. 
To accommodate for multiple purchases of the same stock, each stock is further organized into list of purchases for that 
stock. For example, if an individual buys Tesla stock on 3 different occasions, all 3 purchases would be organized 
under Tesla. This organization allows users the ability to view purchases they made for a specific stock. 

It's important to note that the user input the stock **ticker** when they buy/sell an investment. This is to ensure 
that each stock is registered only once in the portfolio and thus allows for optimal organization. 

When buying a stock, the user will input the following:
- *Stock ticker*
- *Total Amount of Money Invested*
- *Today's Price/Share*
When it comes time to sell, the portfolio holder chooses which purchase they would like to sell for that given stock. 
For example, going back to the 3 separate purchases of Tesla, if the user wants to sell their 2nd investment for Tesla
then the user has this ability. In addition, the user can decide on the percentage of shares they wish to sell. The 
updated investment will exist in the portfolio. If the user decides to sell all their shares for that investment,
the investment will not be removed from the portfolio as I believe this to be important data much like a receipt. 


## Reason For This Project... 

I created this Investment Portfolio application because I wanted a platform that could be used by any person... 
What I mean by this is that this application is meant for anyone who wants to or is already investing. For anyone who 
finds it difficult to stay organized (like myself), this application helps with that. 


## User Stories 
- As a user, I want to be able to add an investment into my portfolio 
- As a user, I want to be able to select the investment that I want to sell 
- As a user, I want to be able to select a portion of my investments to sell 
- As a user, I want to be able to view stock tickers in my current portfolio 
- As a user, I want to be able to view information of all my investments for a given stock ticker in my portfolio 
- As a user, I want to be able to see my total profits (gain/loss) from selling an investment 

- As a user, I want to be able to save all my purchased investments in my portfolio to file 
- As a user, I want to be able to load all my purchased investments in my portfolio from file 


## Phase 4: Task 2
The following is a representative sample of the events that should be logged after **purchasing** investments

- Wed Mar 30 13:02:25 PDT 2022 

4.0 shares of TESLA purchased at 100.0 has been added to your portfolio

- Wed Mar 30 13:02:30 PDT 2022

2.5 shares of TESLA purchased at 200.0 has been added to your portfolio

- Wed Mar 30 13:02:35 PDT 2022

3.0 shares of APPLE purchased at 200.0 has been added to your portfolio

- Wed Mar 30 13:02:41 PDT 2022

7.0 shares of AMC purchased at 100.0 has been added to your portfolio


The following is a representative sample of the events that should be logged after **selling** investments

- Wed Mar 30 13:02:54 PDT 2022

1.5 shares of APPLE purchased at 200.0 remains in your portfolio

- Wed Mar 30 13:03:11 PDT 2022

2.8 shares of TESLA purchased at 100.0 remains in your portfolio

## Phase 4: Task 3
My overall satisfaction for this project is high. I feel accomplished with what I have done so far and I hope to revisit this project 
later down the road. With that said, the following are some improvements that I plan to make as we come close to the end of term.  
- **FIRST** : My InvestmentAppGUI class can use some organization. I think that I could split up some of the functionalities into 
              separate classes. For instance, the JButtons could be a separate class which would allow for easier navigation. This 
              in turn would make the single-responsibility aspect more fulfilling. 
- **SECOND** : I would use constants more throughout my project. This would give me the opportunity to quickly change 
               certain aspects (ie. placement of buttons on JFrame) from a single point. This relays back to  
               single-point of control that I should have realized when developing my InvestmentAppGUI class
- **THIRD** : I would like to use exceptions throughout my project. I think I had planned on doing this after we 
              were introduced to the concept, but at the time, I really didn't understand the exact purpose for using 
              exceptions. Now that I understand how they are used and their purpose, using exceptions will be an improvement
              that I can implement to the code. 

