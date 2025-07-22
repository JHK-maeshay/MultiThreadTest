### JAVA Factory

Make factory with **multithreading**. Also learn about **Producer-Consumer Pattern** and Java's **Data Structures**.
<br>
<br>

> Simple Flow Chart!

```mermaid
flowchart TD
    A[Producer] -->B(Conveyor)
	I[Producer] -->J(Conveyor)
    B --> C(Distributor)
	J --> C
    C --> D(Conveyor)
	C --> E(Conveyor)
    D -->G[Warehouse]
    E -->H[Warehouse]
```

<br>
<br>

> Simple Class Info!

```mermaid
classDiagram
    class Product:::blue{
		+ LocalDateTime productionTime
		+ LocalDateTime expirationTime
}

classDef blue fill:#dff
```

```mermaid
classDiagram
	class Producer{
		+ MAX_STACK_SIZE = 10
		+ Stack＜Product＞ stack
		+ long producingTime
		+ run()
}
	class Conveyor{
		+ Queue＜Product＞ queue
		+ long length
		+ addProduct(Product)
		+ run()
}
Producer --> Conveyor : stack
	class Distributor{
		+
}

Conveyor --> Distributor : queue
Distributor --> Conveyor : split()

	class Warehouse{
		+
}

Conveyor --> Warehouse : queue
```

```mermaid
classDiagram
    class Command:::creen{
        interface*
        + execute()
}
    class CommandContext{
        + Map＜string,$var＞ $varMap
        + Map＜string,$var＞ $varMap
        + ...
        + show$Var()
}
    class Create`{$var}`:::green{
        + execute()
        + ...
}
    class List`{$var}`:::green{
        + execute()
        + ...
}

    class `{...}`:::green{
        + execute()
        + ...
}

    class CommandRegistry:::green{
        + Map＜String, Command＞ commandMap
        + CommandContext context
        + getCommand(command)
}
    class CommandHandler:::green{
        + CommandRegistry registry
        + handle(command)
}

Command --|> Create`{$var}` 
Command --|> List`{$var}`
Command --|> `{...}`
CommandContext --> Create`{$var}` 
CommandContext --> List`{$var}`
CommandContext --> `{...}`
Create`{$var}` --> CommandRegistry
List`{$var}` --> CommandRegistry
`{...}` --> CommandRegistry
CommandRegistry --> CommandHandler
CommandRegistry --> Main
CommandHandler --> Main
classDef creen fill:#efe
classDef green fill:#cfc
```