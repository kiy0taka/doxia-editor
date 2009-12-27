import javax.swing.*

application(title:'Doxia Editor', size:[800,600], locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]
) {
    tabbedPane(stateChanged:controller.&show) {
        scrollPane(name:'Edit') {
            textArea(text:bind(target:model, targetProperty:'source'), tabSize:2)
        }
        scrollPane(name:'View') {
            editorPane(id:'viewer', text:bind{model.html}, contentType:'text/html', editable:false)
        }
    }
}