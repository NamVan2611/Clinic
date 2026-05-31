/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{ts,tsx}'],
  theme: {
    extend: {
      colors: {
        'surface-tint': '#006591',
        surface: '#f8f9ff',
        'secondary-fixed-dim': '#4fdbc8',
        'on-secondary-fixed': '#00201c',
        'outline-variant': '#bec8d2',
        'secondary-container': '#6df5e1',
        'primary-fixed': '#c9e6ff',
        'surface-bright': '#f8f9ff',
        'surface-container-lowest': '#ffffff',
        'on-background': '#0b1c30',
        outline: '#6e7881',
        'surface-variant': '#d3e4fe',
        'error-container': '#ffdad6',
        'surface-container-low': '#eff4ff',
        'surface-container': '#e5eeff',
        error: '#ba1a1a',
        'inverse-primary': '#89ceff',
        secondary: '#006b5f',
        'on-surface': '#0b1c30',
        tertiary: '#8a5100',
        'on-error-container': '#93000a',
        'surface-dim': '#cbdbf5',
        'tertiary-fixed-dim': '#ffb86e',
        'tertiary-fixed': '#ffdcbd',
        'tertiary-container': '#de8712',
        'on-error': '#ffffff',
        'on-secondary': '#ffffff',
        'surface-container-high': '#dce9ff',
        'secondary-fixed': '#71f8e4',
        'on-secondary-fixed-variant': '#005048',
        'on-tertiary-container': '#4d2b00',
        background: '#f8f9ff',
        'on-primary-fixed-variant': '#004c6e',
        'inverse-on-surface': '#eaf1ff',
        'on-primary-fixed': '#001e2f',
        'on-surface-variant': '#3e4850',
        'inverse-surface': '#213145',
        'primary-container': '#0ea5e9',
        'on-primary': '#ffffff',
        'surface-container-highest': '#d3e4fe',
        'on-secondary-container': '#006f64',
        'on-tertiary-fixed-variant': '#693c00',
        'on-primary-container': '#003751',
        primary: '#006591',
        'on-tertiary-fixed': '#2c1600',
        'on-tertiary': '#ffffff',
        'primary-fixed-dim': '#89ceff'
      },
      borderRadius: {
        DEFAULT: '0.25rem',
        lg: '0.5rem',
        xl: '0.75rem',
        full: '9999px'
      },
      spacing: {
        gutter: '24px',
        'stack-md': '16px',
        'sidebar-width': '260px',
        'stack-sm': '8px',
        base: '4px',
        'stack-lg': '24px',
        'container-padding': '32px'
      },
      fontFamily: {
        'label-md': ['Inter', 'sans-serif'],
        'body-lg': ['Inter', 'sans-serif'],
        'headline-lg': ['Inter', 'sans-serif'],
        'headline-md': ['Inter', 'sans-serif'],
        'headline-sm': ['Inter', 'sans-serif'],
        'body-md': ['Inter', 'sans-serif']
      },
      fontSize: {
        'label-md': ['13px', { lineHeight: '18px', fontWeight: '500' }],
        'body-lg': ['16px', { lineHeight: '26px', fontWeight: '400' }],
        'headline-lg': ['24px', { lineHeight: '32px', letterSpacing: '-0.01em', fontWeight: '600' }],
        'headline-md': ['20px', { lineHeight: '28px', fontWeight: '600' }],
        'headline-sm': ['16px', { lineHeight: '24px', fontWeight: '600' }],
        'body-md': ['14px', { lineHeight: '20px', fontWeight: '400' }],
        'label-sm': ['12px', { lineHeight: '16px', fontWeight: '500' }]
      },
      keyframes: {
        float: {
          '0%, 100%': { transform: 'translateY(0px) rotate(0deg)', opacity: '0.8' },
          '50%': { transform: 'translateY(-16px) rotate(180deg)', opacity: '1' }
        }
      },
      animation: {
        float: 'float 6s ease-in-out infinite'
      }
    }
  },
  plugins: []
};
