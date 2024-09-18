Feature: Purchase Orders file handling

    Scenario: Verify purchase_orders.xls file
        Given purchase_orders.xls file exists
        When the user opens purchase_orders.xls
        Then the xls file has the following headers
            | Buyer | Buyer | Buyer External ID | Sales Order |
        And the xls file has more than 10 lines