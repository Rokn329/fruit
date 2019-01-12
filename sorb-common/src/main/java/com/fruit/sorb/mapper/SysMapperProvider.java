package com.fruit.sorb.mapper;

import com.github.abel533.mapper.MapperProvider;
import com.github.abel533.mapperhelper.EntityHelper;
import com.github.abel533.mapperhelper.MapperHelper;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.scripting.xmltags.*;

import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

public class SysMapperProvider extends MapperProvider {

    public SysMapperProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public SqlNode deleteByIDS(MappedStatement ms) {
        Class<?> entityClass = getSelectReturnType(ms);
        Set<EntityHelper.EntityColumn> entityColumns = EntityHelper.getPKColumns(entityClass);
        EntityHelper.EntityColumn column = null;
        for (EntityHelper.EntityColumn entityColumn : entityColumns) {
            column = entityColumn;
            break;
        }

        List<SqlNode> sqlNodes = new ArrayList<SqlNode>();
        // 开始拼sql
        BEGIN();
        // delete from table
        DELETE_FROM(tableName(entityClass));
        // 得到sql
        String sql = SQL();
        // 静态SQL部分
        sqlNodes.add(new StaticTextSqlNode(sql + " WHERE " + column.getColumn() + " IN "));
        // 构造foreach sql
        SqlNode foreach = new ForEachSqlNode(ms.getConfiguration(), new StaticTextSqlNode("#{"
                + column.getProperty() + "}"), "ids", "index", column.getProperty(), "(", ")", ",");
        sqlNodes.add(foreach);
        return new MixedSqlNode(sqlNodes);

    }

    public SqlNode findCount(MappedStatement ms) throws ClassNotFoundException {
        // 1、获取具体方法路径
        String path = ms.getId();
        // 2、获取接口路径
        String classPath = path.substring(0, path.lastIndexOf("."));
        // 3、获取class对象
        Class<?> targetClass = Class.forName(classPath); //forName(classPath);

        Type[] types = targetClass.getGenericInterfaces();
        Type type = types[0];

        // 先判断是否为泛型，为泛型则进一步处理
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] argTypes = parameterizedType.getActualTypeArguments();
            Class<?> argClass = (Class<?>) argTypes[0];
            Table table = argClass.getAnnotation(Table.class);
            String tableName = table.name();
            String sql = "select count(1) from " + tableName;
            SqlNode sqlNode = new StaticTextSqlNode(sql);
            return sqlNode;
        }

        return null;
    }
}
