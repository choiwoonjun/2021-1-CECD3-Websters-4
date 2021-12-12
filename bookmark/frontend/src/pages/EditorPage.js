import * as React from 'react';
import * as ReactDOM from 'react-dom';
// import { Editor, EditorTools } from "@progress/kendo-react-editor";
// import content from "./content-overview";
import { RichTextEditor } from 'react-rte';
import { CKEditor } from 'ckeditor4-react';
// const {
//   Bold,
//   Italic,
//   Underline,
//   Strikethrough,
//   Subscript,
//   Superscript,
//   AlignLeft,
//   AlignCenter,
//   AlignRight,
//   AlignJustify,
//   Indent,
//   Outdent,
//   OrderedList,
//   UnorderedList,
//   Undo,
//   Redo,
//   FontSize,
//   FontName,
//   FormatBlock,
//   Link,
//   Unlink,
//   InsertImage,
//   ViewHtml,
//   InsertTable,
//   AddRowBefore,
//   AddRowAfter,
//   AddColumnBefore,
//   AddColumnAfter,
//   DeleteRow,
//   DeleteColumn,
//   DeleteTable,
//   MergeCells,
//   SplitCell,
// } = EditorTools;
{
  /* <CKEditor data="<p>Editor's content</p>"/> */
}

{
  /* <Editor
      tools={[
        [Bold, Italic, Underline, Strikethrough],
        [Subscript, Superscript],
        [AlignLeft, AlignCenter, AlignRight, AlignJustify],
        [Indent, Outdent],
        [OrderedList, UnorderedList],
        FontSize,
        FontName,
        FormatBlock,
        [Undo, Redo],
        [Link, Unlink, InsertImage, ViewHtml],
        [InsertTable],
        [AddRowBefore, AddRowAfter, AddColumnBefore, AddColumnAfter],
        [DeleteRow, DeleteColumn, DeleteTable],
        [MergeCells, SplitCell],
      ]}
      contentStyle={{
        height: 630,
      }}
      defaultContent={content}
    /> */
}

const EditorPage = () => {
  return (
    <div>
      <div className="editor-page">
        <h3>Editor Page</h3>
      
      <div class="navbarr">
        <a href="#home">Import</a>
        <a href="#news">Edit</a>
        <a href="#home">Setting</a>
      </div>
      {/* <div>
        <RichTextEditor date="data" />
      </div> */}
      <CKEditor data="<p>Hello from the first editor working with the context!</p>"></CKEditor>
      <div className="button-center">
      <button className="button">Save</button>
      <button>Reset</button>
      </div>
      </div>
    </div>
    
  );
};

// ReactDOM.render(<EditorPage />, document.querySelector("my-app"));
export default EditorPage;
