# AirPortFly
- 航班資訊功能需顯示航班資訊、狀態、時間
- 每3分鐘更新一次
- 錯誤處理
- 顯示六種幣別匯率並預設某種幣別匯率

# 開始使用
**API KEY**   
  
開始使用前請先將 `Const.kt` 內的 `CURRENCY_API_KEY` 修改為您 FreecurrencyAPI 的 API KEY

# 畫面介紹
| 頁面 | 淺色主題 | 深色主題 |
| --- | --- | --- |
| 航班搜尋頁 SearchFragment <br> - 使用 MaterialAutoCompleteTextView 根據輸入的文字提供適合的選項 <br> - 若未輸入或是輸入的機場不存在，則會及時提示 | <img src="screenRecord/light_search.gif" height="500">   | <img src="screenRecord/dark_search.gif" height="500"> |
| 航班列表頁 FlightFragment <br> - 每三分鐘更新一次 <br> - 顯示目前搜尋的航班種類、機場以及最後更新時間 <br> - 若有錯誤發生，則會出現提醒 |  <img src="screenRecord/light_flight.gif" height="500"> | <img src="screenRecord/dark_flight.gif" height="500"> |
| 匯率頁 CurrencyFragment <br> - 每三分鐘更新一次 <br>  - 顯示目前各貨幣的匯率 <br> - 若有錯誤發生，則會出現提醒 <br> - 若未來有新增貨幣，只需於Currency中添加即可 |  <img src="screenRecord/light_currency.gif" height="500"> | <img src="screenRecord/dark_currency.gif" height="500"> |

# 開發環境
- Android Studio Hedgehog | 2023.1.1 Patch 2
- Build #AI-231.9392.1.2311.11330709, built on January 19, 2024
- Runtime version: 17.0.7+0-b2043.56-10550314 amd64
- VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.

# 參考
[Material Design](https://m3.material.io/)  
[航班資訊API](http://e-traffic.taichung.gov.tw/DataAPI/swagger/ui/index#!/AirPortFlyAPI/AirPortFlyAPI_Get)  
[匯率API](https://freecurrencyapi.com/)  