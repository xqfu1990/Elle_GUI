select symbol,Price, Trade_Time,count(*) from test_trades
group by symbol,Price, Trade_Time
having count(*)>1
order by symbol;

select l1.* from test_trades l1
right join (select l2.* from test_trades l2 where OC = 'O'
group by l2.symbol,l2.Price,l2.Trade_Time
having count(*)>1) t
on l1.symbol=t.symbol and l1.Price=t.Price and l1.Trade_Time=t.Trade_Time
order by symbol, Trade_Time;

select l1.* from test_trades l1
right join (select l2.* from test_trades l2 where OC = 'O'
group by l2.symbol,l2.Price,l2.Trade_Time
having count(*)>1) t
on l1.symbol=t.symbol and l1.Price=t.Price and l1.Trade_Time=t.Trade_Time
union
(select null, Trade_Time,null,null,symbol,sum(Q),Price,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null from test_trades
where OC = 'O'
group by symbol,Price, Trade_Time
having count(*)>1)
order by symbol, Trade_Time,Q;