package org.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by DevHoon on 2016. 5. 17.
 */
public class InternationalCommentGenerator extends DefaultCommentGenerator {

	private boolean suppressDate = false;
	private boolean suppressAllComments = false;

	private ResourceBundle messages;
	private String encoding = "UTF-8";

	public InternationalCommentGenerator() {
		Locale locale = Locale.getDefault();
		this.setLocaleResource(locale);
	}

	public void addComment(XmlElement xmlElement) {
		if (!this.suppressAllComments) {
			xmlElement.addElement(new TextElement("<!--"));
			StringBuilder sb = new StringBuilder();
			sb.append("  ");
			sb.append(getMessage("warning"));
			sb.append(" - ");
			sb.append("@mbggenerated");
			xmlElement.addElement(new TextElement(sb.toString()));
			xmlElement.addElement(new TextElement("  " + getMessage("element_do_not_modify")));
			String s = this.getDateString();
			if (s != null) {
				sb.setLength(0);
				sb.append("  ");
				sb.append(getMessage("element_generated_on"));
				sb.append(" ");
				sb.append(s);
				sb.append('.');
				xmlElement.addElement(new TextElement(sb.toString()));
			}

			xmlElement.addElement(new TextElement("-->"));
		}
	}

	public void addRootComment(XmlElement rootElement) {
	}

	public void addConfigurationProperties(Properties properties) {
		this.suppressDate = StringUtility.isTrue(properties.getProperty("suppressDate"));
		this.suppressAllComments = StringUtility.isTrue(properties.getProperty("suppressAllComments"));
		this.encoding = properties.getProperty("encoding");

		// Using JDK7
		//Locale locale = Locale.forLanguageTag(properties.getProperty("locale"));
		if(StringUtility.stringHasValue(properties.getProperty("locale"))) {
			String[] lArray = properties.getProperty("locale").split("_");
			Locale locale = new Locale(lArray[0], lArray[1]);
			this.setLocaleResource(locale);
		}
	}

	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		javaElement.addJavaDocLine(" *");
		StringBuilder sb = new StringBuilder();
		sb.append(" * ");
		sb.append("@mbggenerated");
		if (markAsDoNotDelete) {
			sb.append(" do_not_delete_during_merge");
		}

		String s = this.getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}

		javaElement.addJavaDocLine(sb.toString());
	}

	protected String getDateString() {
		return this.suppressDate ? null : (new Date()).toString();
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			innerClass.addJavaDocLine("/**");

			sb.append(" * ");
			sb.append(getMessage("class_generated_by"));
			innerClass.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("class_corresponds_to"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			innerClass.addJavaDocLine(sb.toString());

			this.addJavadocTag(innerClass, false);
			innerClass.addJavaDocLine(" */");
		}
	}

	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			innerEnum.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("enum_generated_by"));
			innerEnum.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("enum_corresponds_to"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			innerEnum.addJavaDocLine(sb.toString());

			this.addJavadocTag(innerEnum, false);
			innerEnum.addJavaDocLine(" */");
		}
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			field.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("field_generated_by"));
			field.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("field_corresponds_to_column"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			sb.append('.');
			sb.append(introspectedColumn.getActualColumnName());
			field.addJavaDocLine(sb.toString());

			this.addJavadocTag(field, false);
			field.addJavaDocLine(" */");
		}
	}

	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			field.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("field_generated_by"));
			field.addJavaDocLine(sb.toString());

			sb = new StringBuilder();
			sb.append(" * ");
			sb.append(getMessage("field_corresponds_to_table"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			field.addJavaDocLine(sb.toString());

			this.addJavadocTag(field, false);
			field.addJavaDocLine(" */");
		}
	}

	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("method_generated_by"));
			method.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("method_corresponds_to_table"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			method.addJavaDocLine(sb.toString());

			this.addJavadocTag(method, false);
			method.addJavaDocLine(" */");
		}
	}

	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("method_generated_by"));
			method.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("method_return_column"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			sb.append('.');
			sb.append(introspectedColumn.getActualColumnName());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");

			sb.setLength(0);
			sb.append(" * @return ");
			sb.append(getMessage("method_return_value"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			sb.append('.');
			sb.append(introspectedColumn.getActualColumnName());
			method.addJavaDocLine(sb.toString());

			this.addJavadocTag(method, false);
			method.addJavaDocLine(" */");
		}
	}

	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			method.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("method_generated_by"));
			method.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("method_set_value_column"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			sb.append('.');
			sb.append(introspectedColumn.getActualColumnName());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" *");
			Parameter parm = (Parameter) method.getParameters().get(0);

			sb.setLength(0);
			sb.append(" * @param ");
			sb.append(parm.getName());

			sb.append(" ");
			sb.append(getMessage("method_set_value_for"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			sb.append('.');
			sb.append(introspectedColumn.getActualColumnName());
			method.addJavaDocLine(sb.toString());
			this.addJavadocTag(method, false);
			method.addJavaDocLine(" */");
		}
	}

	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (!this.suppressAllComments) {
			StringBuilder sb = new StringBuilder();
			innerClass.addJavaDocLine("/**");
			sb.append(" * ");
			sb.append(getMessage("class_generated_by"));
			innerClass.addJavaDocLine(sb.toString());

			sb.setLength(0);
			sb.append(" * ");
			sb.append(getMessage("class_corresponds_to"));
			sb.append(" ");
			sb.append(introspectedTable.getFullyQualifiedTable());
			innerClass.addJavaDocLine(sb.toString());
			this.addJavadocTag(innerClass, markAsDoNotDelete);
			innerClass.addJavaDocLine(" */");
		}
	}

	private void setLocaleResource(Locale locale) {
		messages = ResourceBundle.getBundle("messages.message", locale);
	}

	private String getMessage(String key) {
		String word;
		try {
			if(messages.getString(key) != null) {
				word = new String(messages.getString(key).getBytes("iso-8859-1"), this.encoding);
			} else {
				word = messages.getString(key);
			}
		} catch(Exception e) {
			word = e.getMessage();
			e.printStackTrace();
		}
		return word;
	}
}