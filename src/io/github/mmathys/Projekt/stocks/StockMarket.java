package io.github.mmathys.Projekt.stocks;

import java.util.ArrayList;
import java.util.List;

public class StockMarket {
	private List<Stock> stocks = new ArrayList<Stock>();
	
	public StockMarket() {
		initMarket();
	}

	private void initMarket() {
		/**
		 * Stocks:
		 * ABC Alphabet
		 * LPI Laredo Petroleum Inc
		 * NBL Noble Energy Inc
		 * OPK Opko Health Inc
		 * XEC Cimarex Energy Co
		 * CRM Salesforce.com Inc
		 * XPO XPO Logistics Inc
		 * SNP Snapchat Inc
		 * BXP Boston Properties Inc
		 * NFX Newfield Exploration Co
		 * GRD Greedy Company Co
		 * RHT Red Hat Inc
		 * MCX Gazprom PAO
		 * AMX America Movil SAB de CV
		 * VZN Verizon Communications
		 * AKS AK Steel Holding Corp.
		 */
		
		Stock[] stocksArray = { 
				  new Stock("ABC", "Alphabet", 					3_000_000_000f, 	.4f)
				, new Stock("LPI", "Laredo Petroleum Inc", 		34_565_121f, 		.3f)
				, new Stock("NBL", "Noble Energy Inc", 			97_003_767f, 		.3f)
				, new Stock("OPK", "Opko Health Inc", 			60_000_000f, 		.2f)
				, new Stock("XEC", "Cimarex Energy Co", 		98_001_123f, 		.3f)
				, new Stock("CRM", "Salesforce.com Inc", 		8_000_000f, 		.5f)
				, new Stock("XPO", "XPO Logistics Inc", 		34_000_343f, 		.4f)
				, new Stock("SNP", "Snapchat Inc", 				1_200_198f, 		.9f)
				, new Stock("BXP", "Boston Properties Inc", 	4_387_232f, 		.1f)
				, new Stock("NFX", "Newfield Exploration Co", 	9_012_564f, 		.3f)
				, new Stock("GRD", "Greedy Company Co", 		2_093_343f, 		.0f)
				, new Stock("RHT", "Red Hat Inc", 				1_203_454f, 		.4f)
				, new Stock("MCX", "Gazprom PAO", 				50_023_234_232f, 	.1f)
				, new Stock("AMX", "America Movil SAB de CV", 	545_232f, 			.2f)
				, new Stock("VZN", "Verizon Communications", 	3_123_145_322f, 	.4f)
				, new Stock("AKS", "K Steel Holding Corp.", 	40_123_121f, 		.2f)
		};
		for(Stock s : stocksArray) {
			getStocks().add(s);
		}
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}
	
	public void nextDay() {
		for(Stock s : stocks) {
			s.cycle();
		}
	}

	public boolean hasStock(String response) {
		response = response.toUpperCase().trim();
		
		for(Stock s : stocks) {
			String name = s.getShortName();
			if(name.equals(response)){
				return true;
			}
		}
		return false;
	}

	public Stock getStockByShortName(String stockShortName) {
		stockShortName = stockShortName.trim().toUpperCase();
		for(Stock s : stocks) {
			if(s.getShortName().equals(stockShortName)) {
				return s;
			}
		}
		return null;
	}
}
