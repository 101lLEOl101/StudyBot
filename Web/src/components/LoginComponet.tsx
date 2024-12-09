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


export function LoginComponet(props: PaperProps) {
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

            <Divider label={"Логин"} labelPosition="center" my="lg" />

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
                </Stack>

                <Group justify="space-between" mt="xl">
                    <Link style={{ textDecoration: 'none', color: 'inherit' }} to={"/registration"}>
                        <Anchor component="button" type="button" c="dimmed" size="xs">
                            Нет аккаунта? Регистрация
                        </Anchor>
                    </Link>
                    <Button type="submit" radius="xl" >
                        Войти
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}