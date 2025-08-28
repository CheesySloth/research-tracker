import ReactMarkdown from 'react-markdown';

export default function MarkdownPreview({ content }) {
  return (
    <div className="markdown-preview">
      <ReactMarkdown>{content || '*Nothing to preview*'}</ReactMarkdown>
    </div>
    
  );
};