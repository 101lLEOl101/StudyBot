import {
    Anchor,
    Button,
    Divider,
    Group,
    Paper,
    PaperProps,
    PasswordInput,
    Stack,
    Text,
    TextInput,
} from '@mantine/core';
import { useForm } from '@mantine/form';
import {Link} from "react-router-dom";


export function RegistrationComponent(props: PaperProps) {
    const form = useForm({
        initialValues: {
            login: '',
            password: '',
            repeat_password: '',
            terms: true,
        },

        validate: {
            password: (val) => (val.length <= 6 ? 'Пароль должен иметь не менее 6 символов' : null),
            repeat_password: (val, vals) => (val !== vals.password ? 'Повторный пароль неверен' : null)
        },
    });

    return (
        <Paper radius="md" p="xl" withBorder {...props}>
            <Text size="lg" ta={"center"} fw={500}>
                Доступ к Study Bot
            </Text>

            <Divider label={'Registration'} labelPosition="center" my="lg" />

            <form onSubmit={form.onSubmit(() => {})}>
                <Stack>
                    <TextInput
                        label="Логин"
                        placeholder="Твой Логин"
                        value={form.values.login}
                        onChange={(event) => form.setFieldValue('login', event.currentTarget.value)}
                        radius="md"
                    />

                    <PasswordInput
                        label="Пароль"
                        placeholder="Твой Пароль"
                        value={form.values.password}
                        onChange={(event) => form.setFieldValue('password', event.currentTarget.value)}
                        error={form.errors.password && 'Пароль должен иметь не менее 6 символов'}
                        radius="md"
                    />

                    <PasswordInput
                        label="Повтор Пароля"
                        placeholder="Твой Пароль"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password && 'Повторный пароль неверен'}
                        radius="md"
                    />
                </Stack>

                <Group justify="space-between" mt="xl">
                    <Link style={{ textDecoration: 'none', color: 'inherit' }} to={"/login"}>
                        <Anchor component="button" type="button" c="dimmed" size="xs">
                            Уже есть аккаунт? Вход
                        </Anchor>
                    </Link>
                    <Button type="submit" radius="xl" >
                        Зарегистрироваться
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}