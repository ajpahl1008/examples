import groovy.json.JsonSlurper

    //def slurper = new JsonSlurper()
    //def file = new File("C:\\Users\\aj\\Desktop\\GitHub\\examples\\pahlsoft-groovy-json-parser\\test.json")
    //def result = slurper.parse(file)


File f = new File("C:\\Users\\aj\\Desktop\\GitHub\\examples\\pahlsoft-groovy-json-parser\\test.json")
def slurper = new JsonSlurper()
def jsonText = f.getText()
    json = slurper.parseText( jsonText )


    json.each{println it}

//    assert result.person.name == "Guillaume"
