	SET SQL_SAFE_UPDATES = 0;
	delete from aggregatedTrades;
	INSERT INTO aggregatedTrades (id,account,yr,t_grp)
	select l1.id,l1.account,l1.yr,t.id from trades l1
	right join (select l2.* from trades l2 where OC = 'O'
	group by l2.symbol,l2.Price,l2.Trade_Time
	having count(*)>1) t
	on l1.symbol=t.symbol and l1.Price=t.Price and l1.Trade_Time=t.Trade_Time;

	delete from aggregatedTradesTotals;
	INSERT INTO aggregatedTradesTotals (id,account,yr,t_grp,sumQ)
	select l2.id,l2.account,l2.yr,l2.id,sum(l2.Q) from trades l2
	where OC = 'O'
	group by l2.symbol,l2.Price,l2.Trade_Time
	having count(*)>1;
    
	select p.* from trades p
	right join aggregatedTrades q
	on p.id=q.id
	union 
	(select t.id, t.trade_Time,null,null,t.symbol,aa.sumQ,t.price,
	null,null,null,null,null,null,null,null,null,null,aa.account,aa.yr,
	null,null,null,null,null,null,null,null,null,null,null,null,null,null,
	null,aa.t_grp,null,null
	 from trades t
	 right join aggregatedTradesTotals aa
	 on t.id = aa.id)
	order by symbol, Trade_Time,Q;