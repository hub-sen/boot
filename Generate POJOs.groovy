import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil
import java.text.SimpleDateFormat

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */

packageName = "org.shisen.web.models.dto;"

// TODO 待完善
typeMapping = [
        (~/(?i)int/)                      : "long",
        (~/(?i)float|double|decimal|real/): "double",
        (~/(?i)datetime|timestamp/)       : "LocalDateTime",
        (~/(?i)date/)                     : "java.util.Date",
        (~/(?i)time/)                     : "java.util.Time",
        (~/(?i)/)                         : "String"
]

FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}


def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "") + ";"
}

def generate(table, dir) {
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    new File(dir, className + "Dto.java").withPrintWriter("utf-8") { out -> generate(dir,out, className, fields, table) }
}

def generate(dir, out, className, fields, table) {

    out.println "package "+ getPackageName(dir)
//    out.println "package $packageName"
    out.println ""
    out.println "import lombok.Getter;"
    out.println "import lombok.Setter;"
    out.println "import lombok.experimental.Accessors;"
    out.println "import org.shisen.web.models.BaseModel;"
    out.println ""
    out.println "import javax.persistence.Column;"
    out.println "import javax.persistence.Table;"
    out.println ""
    out.println "/**\n" +
            " * @author shishi\n" +
            " * @date "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " \n" +
            " */"
    out.println ""
    out.println "@Accessors(chain = true)"
    out.println "@Getter"
    out.println "@Setter"
    out.println "@Table(name = \""+ table.getName() +"\")"
    out.println "public class $className"+"Dto extends BaseModel implements BaseDto {"
    fields.each() {
        if (it.name != "deleted" && it.name != "createdTime" && it.name != "updatedTime") {
            out.println ""
            out.println "\t/**"
            out.println "\t * ${it.comment}"
            out.println "\t */"
            if (it.annos != "") out.println "  ${it.annos}"
            out.println "\tprivate ${it.type} ${it.name};"
        }
    }
    out.println ""
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def commentStr = col.getComment()
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name : javaName(col.getName(), false),
                           type : typeStr,
                           annos: "\t@Column(name = \""+col.getName()+"\")",
                           comment : commentStr]]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}
