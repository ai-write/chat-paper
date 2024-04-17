interface WriteConfig {
    configName: string,
    configKey: string,
    configIcon: string,
    isDefault: boolean
}

interface WriteConfigMaterial {
    key: string,
    value: string
}

interface WriteConfigInput {
    name: string,
    description?: string,
    formKey: string,
    formType: string,
    span?: string,
    tip?: string,
    rule?: string,
    dictData?: Array<Dict>
}

interface WriteConfigDetail {
    inputs: Array<WriteConfigInput>,
    materialList?: Array<WriteConfigMaterial>
}

interface PaperOutlineSubmit {
    forms:Array<KeyValue>,
    materials?:Array<WriteConfigMaterial>
}


interface PaperOutline {
    id: string,
    parentId: string,
    title: string,
    level: number,
    chapter: string,
    description?: string,
    children?: Array<PaperOutline>
}

interface PaperOutlineResponse {
    outlines: Array<PaperOutline>,
    paperId: string
}
