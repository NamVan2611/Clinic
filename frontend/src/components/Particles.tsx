import { useMemo } from 'react';

const particles = Array.from({ length: 14 }, (_, index) => ({
  id: index,
  left: `${Math.random() * 98}%`,
  top: `${Math.random() * 98}%`,
  size: `${Math.random() * 8 + 4}px`,
  delay: `${Math.random() * 2}s`,
  duration: `${4 + Math.random() * 4}s`
}));

const Particles = () => {
  const items = useMemo(() => particles, []);

  return (
    <div className="absolute inset-0 pointer-events-none">
      {items.map((dot) => (
        <span
          key={dot.id}
          className="absolute block rounded-full bg-primary-container/25"
          style={{
            width: dot.size,
            height: dot.size,
            left: dot.left,
            top: dot.top,
            animation: `float ${dot.duration} ease-in-out infinite`,
            animationDelay: dot.delay
          }}
        />
      ))}
    </div>
  );
};

export default Particles;
