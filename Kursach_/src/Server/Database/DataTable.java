package Server.Database;

import java.sql.Statement;

public abstract class DataTable{
    protected Statement statement;
    protected Database database;

    public DataTable(Statement statement, Database database) {
        this.statement = statement;
        this.database = database;
    }
}
