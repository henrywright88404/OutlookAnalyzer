package outlookanalyzer.outlookconnection;

import com.microsoft.graph.options.QueryOption;

public interface MailOptions {
    QueryOption orderByCreatedDateTimeDecending = new QueryOption("orderby", "createdDateTime DESC");
    QueryOption returnCountTrue = new QueryOption("count","true");
    QueryOption returnCountFalse = new QueryOption("count","false");
}
