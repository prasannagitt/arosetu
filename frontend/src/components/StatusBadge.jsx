export default function StatusBadge({ status }) {
  const colors = {
    REQUESTED: 'bg-yellow-100 text-yellow-800',
    PENDING: 'bg-yellow-100 text-yellow-800',
    DRAFT: 'bg-yellow-100 text-yellow-800',
    CONFIRMED: 'bg-blue-100 text-blue-800',
    APPROVED: 'bg-blue-100 text-blue-800',
    ACCEPTED: 'bg-blue-100 text-blue-800',
    PRESCRIBED: 'bg-blue-100 text-blue-800',
    COMPLETED: 'bg-green-100 text-green-800',
    DISPENSED: 'bg-green-100 text-green-800',
    CANCELLED: 'bg-red-100 text-red-800',
    REJECTED: 'bg-red-100 text-red-800',
    IN_PROGRESS: 'bg-purple-100 text-purple-800',
    PROCESSING: 'bg-purple-100 text-purple-800',
    SAMPLE_COLLECTED: 'bg-purple-100 text-purple-800',
    SENT_TO_PHARMACY: 'bg-purple-100 text-purple-800',
    READY: 'bg-indigo-100 text-indigo-800',
  };

  const colorClass = colors[status] || 'bg-gray-100 text-gray-800';

  return (
    <span className={`inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium ${colorClass}`}>
      {status?.replace(/_/g, ' ')}
    </span>
  );
}

