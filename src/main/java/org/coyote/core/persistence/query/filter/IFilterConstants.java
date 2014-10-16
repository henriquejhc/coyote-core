package org.coyote.core.persistence.query.filter;

/**
 * 
 * @author Jose Henrique Cardoso
 *
 */
@Deprecated
public interface IFilterConstants {

	public static final String SELECT_COUNT = " select count(entity) from class as entity ";
	public static final String SELECT = " from class as entity ";
	public static final String WHERE = " where ";
	public static final String ORDER_BY = " order by orderBy ";
	public static final Integer NO_PAGINATOR = -1;
	public static final String DIFFERENT = " <> ";
	public static final String LIKE = ") like ";
	public static final String OR = " or ";
	public static final String OPEN = "open";
	public static final String CLOSE = "close";
	public static final String MAIOR = " > ";
	public static final String MENOR = " < ";
	public static final String MAIOR_OU_IGUAL = " >= ";
	public static final String MENOR_OU_IGUAL = " <= ";
	public static final String IS_NOT_NULL = " is not null ";
	public static final String IS_NULL = " is null ";
	public static final String VALUE_NULL = " valueNull ";

}
