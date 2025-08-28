export default function NoteCard({ note, onClick }) {
  return (
    <div className="note-card" onClick={onClick}>
      <h2>{note.title}</h2>
      <h3>{note.author}</h3>
      <p className="note-preview">
        {note.content.substring(0, 100)}{note.content.length > 100 ? '...' : ''}
      </p>
      <div className="note-tags">
        {note.tags.split(',').map(tag => (
          <span key={tag} className="tag"> {tag.trim()}</span>
        ))}
      </div>
    </div>
  );
};