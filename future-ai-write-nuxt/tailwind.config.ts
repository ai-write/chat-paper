import type {Config} from "tailwindcss";

export default <Partial<Config>>{
    darkMode: "class",
    theme: {
        extend: {
            borderColor: {
                'primary': 'var(--primary-color)'
            },
            textColor: {
                'primary': 'var(--primary-color)',
                'normal': 'var(--text-primary-color)',
                'description': 'var(--text-description-color)'
            }
        }
    },
}

