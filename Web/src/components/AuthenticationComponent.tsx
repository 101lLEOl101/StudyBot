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
import { upperFirst, useToggle } from '@mantine/hooks';
import {useQuery} from "@tanstack/react-query";

type User = {
    id: string;
    text: string;
    done: boolean;
    login: string;
    password: string;
}

export const getTasks = () =>{
    return new Promise<User[]>(res =>{
        setTimeout(()=> {
            res([{
                id: "1",
                text: "done",
                done: true,
                login: "login",
                password: "password",
            }, {
                id: "2",
                text: "yes",
                done: true,
                login: "abcd",
                password: "efghig",
            },
            ])
        }, 100)
    })
}

export function AuthenticationComponent(props: PaperProps) {
    const { data, error, isLoading } = useQuery({queryKey: ['users', 'list'], queryFn: getTasks});
    if(isLoading){
        console.log("loading");
    }

    else if(error){
        console.log(error);
    }

    else{
        console.log(data);
    }

    const [type, toggle] = useToggle(['Login', 'Registration']);
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

            <Divider label={type == 'Registration' ? 'Регистрация' : 'Логин'} labelPosition="center" my="lg" />

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

                    {type === 'Registration' && (<PasswordInput
                        label="Повтор Пароля"
                        placeholder="Твой Пароль"
                        value={form.values.repeat_password}
                        onChange={(event) => form.setFieldValue('repeat_password', event.currentTarget.value)}
                        error={form.errors.repeat_password && 'Повторный пароль неверен'}
                        radius="md"
                    />)}
                </Stack>

                <Group justify="space-between" mt="xl">
                    <Anchor component="button" type="button" c="dimmed" onClick={() => toggle()} size="xs">
                        {type === 'Registration'
                            ? 'Уже есть аккаунт? Вход'
                            : "Нет аккаунта? Регистрация"}
                    </Anchor>
                    <Button type="submit" radius="xl">
                        {upperFirst(type == 'Registration' ? 'Зарегистрироваться' : 'Войти')}
                    </Button>
                </Group>
            </form>
        </Paper>
    );
}